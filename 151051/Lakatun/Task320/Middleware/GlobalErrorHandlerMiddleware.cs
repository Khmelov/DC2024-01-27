using FluentValidation;
using Task320.Exceptions;
using Microsoft.EntityFrameworkCore;

namespace Task320.Middleware
{
    public class GlobalErrorHandlerMiddleware(RequestDelegate next)
    {
        private readonly RequestDelegate _next = next;

        public async Task InvokeAsync(HttpContext context)
        {
            try
            {
                await _next(context);
            }
            catch (Exception ex)
            {
                context.Response.StatusCode = ex switch
                {
                    ValidationException => StatusCodes.Status400BadRequest,
                    NotFoundException => StatusCodes.Status404NotFound,
                    DbUpdateException => StatusCodes.Status403Forbidden,
                    _ => StatusCodes.Status500InternalServerError
                };
                await context.Response.WriteAsJsonAsync(new { ErrorMessage = ex.Message });
            }
        }
    }

    public static class GlobalErrorHandlerMiddlewareExtension
    {
        public static IApplicationBuilder UseGlobalErrorHandler(this IApplicationBuilder app)
        {
            return app.UseMiddleware<GlobalErrorHandlerMiddleware>();
        }
    }
}

using Task320.Extensions;
using Task320.Middleware;

var builder = WebApplication.CreateBuilder(args);

builder.Services
    .AddControllers()
    .AddNewtonsoftJson(options =>
        options.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore
    );
builder.Services.AddApiVersioning();
builder.Services
    .AddDbContext(builder.Configuration)
    .AddEndpointsApiExplorer()
    .AddSwaggerGen()
    .AddServices()
    .AddRepositories()
    .AddInfrastructure();

var app = builder.Build();

app.UseGlobalErrorHandler();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseAuthorization();

app.MapControllers();

app.Run();

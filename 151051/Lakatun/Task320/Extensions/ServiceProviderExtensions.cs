using FluentValidation;
using Task320.Data;
using Task320.Infrastructure.Mapper;
using Task320.Infrastructure.Validators;
using Task320.Repositories.Implementations;
using Task320.Repositories.Interfaces;
using Task320.Services.Implementations;
using Task320.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace Task320.Extensions
{
    public static class ServiceProviderExtensions
    {
        public static IServiceCollection AddDbContext(this IServiceCollection services, IConfigurationManager config)
        {
            services.AddDbContext<AppDbContext>(options =>
                options
                .UseLazyLoadingProxies()
                .UseNpgsql(config.GetConnectionString("PostgresConnection")));
            return services;
        }

        public static IServiceCollection AddRepositories(this IServiceCollection services)
        {
            services.AddScoped<ICreatorRepository, CreatorSqlRepository>();
            services.AddScoped<INewsRepository, NewsSqlRepository>();
            services.AddScoped<IStickerRepository, StickerSqlRepository>();
            services.AddScoped<INoteRepository, NoteSqlRepository>();

            return services;
        }

        public static IServiceCollection AddServices(this IServiceCollection services)
        {
            services.AddScoped<ICreatorService, CreatorService>();
            services.AddScoped<INewsService, NewsService>();
            services.AddScoped<IStickerService, StickerService>();
            services.AddScoped<INoteService, NoteService>();

            return services;
        }

        public static IServiceCollection AddInfrastructure(this IServiceCollection services)
        {
            services.AddAutoMapper(typeof(MappingProfile));
            services.AddValidatorsFromAssemblyContaining<CreatorRequestDtoValidator>();

            return services;
        }
    }
}

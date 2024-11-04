using FluentValidation;
using Task350.Publisher.Data;
using Task350.Publisher.HttpClients.Implementations;
using Task350.Publisher.HttpClients.Interfaces;
using Task350.Publisher.Infrastructure.Mapper;
using Task350.Publisher.Infrastructure.Validators;
using Task350.Publisher.Repositories.Implementations;
using Task350.Publisher.Repositories.Interfaces;
using Task350.Publisher.Services.Implementations;
using Task350.Publisher.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace Task350.Publisher.Extensions;

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

        return services;
    }

    public static IServiceCollection AddServices(this IServiceCollection services)
    {
        services.AddScoped<ICreatorService, CreatorService>();
        services.AddScoped<INewsService, NewsService>();
        services.AddScoped<IDiscussionClient, DiscussionClient>();
        services.AddScoped<IStickerService, StickerService>();

        return services;
    }

    public static IServiceCollection AddDiscussionClient(this IServiceCollection services)
    {
        services
            .AddHttpClient(nameof(DiscussionClient), client => client.BaseAddress = new Uri("http://localhost:24130/api/v1/"));

        return services;
    }

    public static IServiceCollection AddInfrastructure(this IServiceCollection services)
    {
        services.AddAutoMapper(typeof(MappingProfile));
        services.AddValidatorsFromAssemblyContaining<CreatorRequestDtoValidator>();

        return services;
    }

    public static IServiceCollection AddKafka(this IServiceCollection services)
    {
        services.AddAutoMapper(typeof(MappingProfile));
        services.AddValidatorsFromAssemblyContaining<CreatorRequestDtoValidator>();

        return services;
    }

    public static void ApplyMigrations(this IApplicationBuilder app, IServiceProvider services)
    {
        using var scope = services.CreateScope();
        var _db = scope.ServiceProvider.GetRequiredService<AppDbContext>();

        if (_db.Database.GetPendingMigrations().Any())
        {
            _db.Database.Migrate();
        }
    }
}
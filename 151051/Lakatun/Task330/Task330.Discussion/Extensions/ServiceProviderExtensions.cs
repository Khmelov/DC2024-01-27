using Cassandra;
using FluentValidation;
using Task330.Discussion.Data;
using Task330.Discussion.Infrastructure.Mapper;
using Task330.Discussion.Infrastructure.Validators;
using Task330.Discussion.Models;
using Task330.Discussion.Repositories.Implementations;
using Task330.Discussion.Repositories.Interfaces;
using Task330.Discussion.Services.Implementations;
using Task330.Discussion.Services.Interfaces;

namespace Task330.Discussion.Extensions
{
    public static class ServiceProviderExtensions
    {
        public static IServiceCollection AddCassandra(this IServiceCollection services, IConfiguration config)
        {
            var address = config.GetValue<string>("Cassandra:Address");
            var schema = config.GetValue<string>("Cassandra:Schema");
            var session = new CassandraConnector(address!, schema!).GetSession();
            services.AddSingleton(session);
            
            return services;
        }

        public static IServiceCollection AddRepositories(this IServiceCollection services)
        {
            services.AddScoped<ICassandraRepository<Note>, CassandraNoteRepository>();

            return services;
        }

        public static IServiceCollection AddServices(this IServiceCollection services)
        {
            services.AddScoped<INoteService, NoteService>();

            return services;
        }

        public static IServiceCollection AddInfrastructure(this IServiceCollection services)
        {
            services.AddAutoMapper(typeof(MappingProfile));
            services.AddValidatorsFromAssemblyContaining<NoteRequestDtoValidator>();

            return services;
        }
    }
}

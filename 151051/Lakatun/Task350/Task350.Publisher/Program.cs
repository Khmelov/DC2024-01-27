using Task340.Messaging.Extensions;
using Newtonsoft.Json;
using Task340.Messaging;
using Task350.Publisher.DTO.ResponseDTO;
using Task350.Publisher.Consumers;
using Task350.Publisher.Extensions;
using Task350.Publisher.Middleware;
using Task350.Publisher.DTO.RequestDTO;

var builder = WebApplication.CreateBuilder(args);

builder.Services
    .AddControllers()
    .AddNewtonsoftJson(options =>
        options.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore
    );
builder.Services.AddApiVersioning();
builder.Services
    .AddDbContext(builder.Configuration)
    .AddEndpointsApiExplorer()
    .AddSwaggerGen()
    .AddServices()
    .AddRepositories()
    .AddDiscussionClient()
    .AddInfrastructure()
    .AddKafkaMessageBus()
    .AddKafkaProducer<string, KafkaMessage<NoteRequestDto>>(options =>
    {
        options.Topic = "InTopic";
        options.BootstrapServers = "localhost:9092";
    })
    .AddKafkaConsumer<string, KafkaMessage<NoteResponseDto>, OutTopicHandler>(options =>
    {
        options.Topic = "OutTopic";
        options.GroupId = "notes-group";
        options.BootstrapServers = "localhost:9092";
    });

builder.Services.AddStackExchangeRedisCache(options =>
{
    options.Configuration = builder.Configuration.GetConnectionString("RedisConnection");
});

var app = builder.Build();

app.UseGlobalErrorHandler();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseAuthorization();

app.MapControllers();

app.ApplyMigrations(app.Services);

app.Run();
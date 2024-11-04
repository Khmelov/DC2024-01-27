using Lab4.Publisher.Extensions;
using Lab4.Publisher.Middleware;
using Lab4.Messaging.Extensions;
using Newtonsoft.Json;
using Lab4.Messaging;
using Lab4.Publisher.DTO.ResponseDTO;
using Lab4.Publisher.DTO.RequestDTO;
using Lab4.Publisher.Consumers;

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
    })
    .AddKafkaConsumer<string, KafkaMessage<NoteResponseDto>, OutTopicHandler>(options =>
    {
        options.Topic = "OutTopic";
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
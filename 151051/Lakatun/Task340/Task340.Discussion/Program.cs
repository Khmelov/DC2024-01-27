using Lab4.Discussion.Extensions;
using Lab4.Discussion.Middleware;
using Lab4.Discussion.DTO.ResponseDTO;
using Lab4.Discussion.DTO.RequestDTO;
using Lab4.Discussion.Consumers;
using Lab4.Messaging.Extensions;
using Lab4.Messaging;

var builder = WebApplication.CreateBuilder(args);

builder.Services
    .AddControllers()
    .AddNewtonsoftJson(options =>
        options.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore);
builder.Services.AddApiVersioning();
builder.Services
    .AddCassandra(builder.Configuration)
    .AddEndpointsApiExplorer()
    .AddSwaggerGen()
    .AddServices()
    .AddRepositories()
    .AddInfrastructure()
    .AddKafkaProducer<string, KafkaMessage<NoteResponseDto>>(options =>
    {
        options.Topic = "OutTopic";
    })
    .AddKafkaConsumer<string, KafkaMessage<NoteRequestDto>, InTopicHandler>(options =>
    {
        options.Topic = "InTopic";
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

app.Run();

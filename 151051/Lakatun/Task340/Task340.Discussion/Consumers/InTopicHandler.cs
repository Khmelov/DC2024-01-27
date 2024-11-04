using Task340.Messaging.Consumer;
using Task340.Messaging;
using Task340.Discussion.DTO.RequestDTO;

namespace Task340.Discussion.Consumers
{
    public class InTopicHandler : IKafkaHandler<string, KafkaMessage<NoteRequestDto>>
    {
        public async Task HandleAsync(string key, KafkaMessage<NoteRequestDto> value)
        {
        }
    }
}

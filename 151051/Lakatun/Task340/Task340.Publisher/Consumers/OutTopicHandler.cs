using Task340.Messaging.Consumer;
using Task340.Messaging;
using Task340.Publisher.DTO.ResponseDTO;

namespace Task340.Publisher.Consumers
{
    public class OutTopicHandler : IKafkaHandler<string, KafkaMessage<NoteResponseDto>>
    {
        public async Task HandleAsync(string key, KafkaMessage<NoteResponseDto> value)
        {
        }
    }
}

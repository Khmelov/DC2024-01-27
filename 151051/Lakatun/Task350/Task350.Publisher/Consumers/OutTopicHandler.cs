using Task340.Messaging.Consumer;
using Task340.Messaging;
using Task350.Publisher.DTO.ResponseDTO;

namespace Task350.Publisher.Consumers
{
    public class OutTopicHandler : IKafkaHandler<string, KafkaMessage<NoteResponseDto>>
    {
        public async Task HandleAsync(string key, KafkaMessage<NoteResponseDto> value)
        {
        }
    }
}

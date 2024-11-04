using Confluent.Kafka;

namespace Task340.Messaging.Producer;

public class KafkaProducerConfig<Tk, Tv> : ProducerConfig
{
    public string Topic { get; set; }
}
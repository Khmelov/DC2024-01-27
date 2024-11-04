using Confluent.Kafka;

namespace Task340.Messaging.Consumer;

public class KafkaConsumerConfig<Tk, Tv> : ConsumerConfig
{
    public string Topic { get; set; }
    public KafkaConsumerConfig()
    {
        AutoOffsetReset = Confluent.Kafka.AutoOffsetReset.Earliest;
        EnableAutoOffsetStore = false;
    }
}
package ekafka

import (
	"encoding/json"
	"fmt"

	"github.com/confluentinc/confluent-kafka-go/v2/kafka"
)

func WithLocalProducer() (*kafka.Producer, error) {
	producer, err := kafka.NewProducer(&kafka.ConfigMap{"bootstrap.servers": "localhost"})
	if err != nil {
		fmt.Println("ERROR:")
		return nil, err
	}
	return producer, nil
}

func ProduceToLocalTopic(topic string, ent any) error {
	producer, err := WithLocalProducer()
	if err != nil {
		return err
	}
	defer producer.Close()

	jsonb, _ := json.Marshal(ent)
	err = producer.Produce(
		&kafka.Message{
			TopicPartition: kafka.TopicPartition{
				Topic:     &topic,
				Partition: kafka.PartitionAny,
			},
			Value: jsonb,
		}, nil,
	)
	if err != nil {
		fmt.Println("ERROR:")
		return err
	}
	producer.Flush(200)
	return nil
}

package ekafka

import (
	"encoding/json"
	"fmt"
	"time"

	"github.com/confluentinc/confluent-kafka-go/v2/kafka"
	"github.com/google/uuid"
)

type EkafkaConsumer struct {
	consumer *kafka.Consumer
}

var cachedConsumers map[string]*EkafkaConsumer

func WithLocalConsumer(topic string) *EkafkaConsumer {
	id, _ := uuid.NewV7()
	consumer, err := kafka.NewConsumer(&kafka.ConfigMap{
		"bootstrap.servers": "localhost",
		"group.id":          "localGroup",
		"group.instance.id": fmt.Sprintf("localGroup-%d", uint(id.ID())*uint(id.ClockSequence())),
		"auto.offset.reset": "earliest",
	})
	if err != nil {
		fmt.Println("ERROR:")
		panic(err)
	}

	err = consumer.Subscribe(topic, nil)
	if err != nil {
		fmt.Println("ERROR:")
		panic(err)
	}

	return &EkafkaConsumer{consumer: consumer}
}

func WithCachedLocalConsumer(topic string) *EkafkaConsumer {
	if cachedConsumers == nil {
		cachedConsumers = make(map[string]*EkafkaConsumer)
	}

	consumer, ok := cachedConsumers[topic]

	if !ok {
		consumer = WithLocalConsumer(topic)
		cachedConsumers[topic] = consumer
	}

	return consumer
}

func (c *EkafkaConsumer) ReadMessage(ent any) bool {
	msg, err := c.consumer.ReadMessage(time.Millisecond * 200)
	if err != nil {
		return false
	}

	json.Unmarshal(msg.Value, ent)
	return true
}

func (c *EkafkaConsumer) Close() {
	c.consumer.Close()
}

package by.bsuir.publisher.client.discussion.kafka.config;


import by.bsuir.publisher.client.discussion.kafka.dto.KafkaRequestTo;
import by.bsuir.publisher.client.discussion.kafka.dto.KafkaResponseTo;
import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    @Value("${topic.tweet-note.request}")
    private String requestTopic;

    @Value("${topic.tweet-note.response}")
    private String responseTopic;

    @Bean
    public ReplyingKafkaTemplate<String, KafkaRequestTo, KafkaResponseTo> replyingKafkaTemplate(
            ProducerFactory<String, KafkaRequestTo> producerFactory,
            KafkaMessageListenerContainer<String, KafkaResponseTo> container
    ) {
        return new ReplyingKafkaTemplate<>(producerFactory, container);
    }

    @Bean
    public KafkaMessageListenerContainer<String, KafkaResponseTo> replyContainer(
            ConsumerFactory<String, KafkaResponseTo> consumerFactory
    ) {
        ContainerProperties containerProperties = new ContainerProperties(responseTopic);
        containerProperties.setGroupId("publisher");

        return new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    }

    @Bean
    public NewTopic requestTopic() {
        return TopicBuilder.name(requestTopic)
                .partitions(4)
                .build();
    }
}

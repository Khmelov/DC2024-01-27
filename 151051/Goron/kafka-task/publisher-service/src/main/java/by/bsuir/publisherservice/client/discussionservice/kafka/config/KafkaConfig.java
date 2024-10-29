package by.bsuir.publisherservice.client.discussionservice.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import by.bsuir.publisherservice.client.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.ResponseTopicMessage;

@Configuration
public class KafkaConfig {

    @Value("${topic.news-message.request}")
    private String requestTopic;
  
    @Value("${topic.news-message.response}")
    private String responseTopic;

    @Bean
    public ReplyingKafkaTemplate<String, RequestTopicMessage, ResponseTopicMessage> replyingKafkaTemplate(
            ProducerFactory<String, RequestTopicMessage> producerFactory,
            KafkaMessageListenerContainer<String, ResponseTopicMessage> container
    ) {
        return new ReplyingKafkaTemplate<>(producerFactory, container);
    }
    
    @Bean
    public KafkaMessageListenerContainer<String, ResponseTopicMessage> replyContainer(
            ConsumerFactory<String, ResponseTopicMessage> consumerFactory
    ) {
        ContainerProperties containerProperties = new ContainerProperties(responseTopic);
        containerProperties.setGroupId("publisher-service");

        KafkaMessageListenerContainer<String, ResponseTopicMessage> container = 
                new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    
        return container;
    }

    @Bean
    public NewTopic requestTopic() {
        return TopicBuilder.name(requestTopic)
                           .partitions(4)
                           .build();
    }

}

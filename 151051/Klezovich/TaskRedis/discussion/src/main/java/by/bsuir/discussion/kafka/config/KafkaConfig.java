package by.bsuir.discussion.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${topic.tweet-note.response}")
    private String responseTopic;

    @Bean
    public NewTopic responseTopic() {
        return TopicBuilder.name(responseTopic)
                .partitions(4)
                .build();
    }
}

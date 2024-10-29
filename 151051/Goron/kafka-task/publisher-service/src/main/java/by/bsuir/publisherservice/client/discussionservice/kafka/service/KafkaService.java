package by.bsuir.publisherservice.client.discussionservice.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import by.bsuir.publisherservice.client.discussionservice.kafka.exception.DiscussionServiceResponseException;
import by.bsuir.publisherservice.client.discussionservice.kafka.exception.DiscussionServiceTimeoutException;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.ResponseTopicMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    @Value("${topic.news-message.request}")
    private String REQUEST_TOPIC;

    private final ReplyingKafkaTemplate<String, RequestTopicMessage, ResponseTopicMessage> REPLYING_KAFKA_TEMPLATE;

    public ResponseTopicMessage sendAndRecieve(RequestTopicMessage topicMessage) {
        log.info("Sending request message: {}", topicMessage);

        // Key to determine a partition by Kafka
        String recordKey = 
                topicMessage.requestMessage() != null && topicMessage.requestMessage().newsId() != null
                ? topicMessage.requestMessage().newsId().toString()
                : null;
        ProducerRecord<String, RequestTopicMessage> record = 
                new ProducerRecord<>(REQUEST_TOPIC, recordKey, topicMessage);
        RequestReplyFuture<String, RequestTopicMessage, ResponseTopicMessage> replyFuture = 
                REPLYING_KAFKA_TEMPLATE.sendAndReceive(record);
                
        try {
            ConsumerRecord<String, ResponseTopicMessage> consumerRecord = replyFuture.get(3, TimeUnit.SECONDS);
            log.info("Recieved response message: {}", consumerRecord.value());
            return consumerRecord.value();
        }
        catch (TimeoutException e) {
            throw new DiscussionServiceTimeoutException("Timeout while trying to get response");
        }
        catch (ExecutionException e) {
            throw new DiscussionServiceResponseException("Unexpected error occured while trying to get response: "
                     + e.getMessage());
        }
        catch (InterruptedException e) {
            throw new DiscussionServiceResponseException("Unexpected error occured while trying to get response: "
                     + e.getMessage());
        }
    }

}

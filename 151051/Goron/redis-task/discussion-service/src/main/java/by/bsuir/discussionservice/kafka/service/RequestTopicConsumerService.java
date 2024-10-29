package by.bsuir.discussionservice.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import by.bsuir.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.discussionservice.kafka.message.ResponseTopicMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestTopicConsumerService {

    private final RequestTopicMessageProcessor MESSAGE_PROCESSOR;

    @Value("${topic.news-message.request}")
    private String requestTopicName;

    @Value("${topic.news-message.response}")
    private String responseTopicName;

    @KafkaListener(id="discussion-service", topics = "${topic.news-message.request}", groupId = "discussion-service")
    @SendTo
    public ResponseTopicMessage listen(ConsumerRecord<String, RequestTopicMessage> message) {
        log.info("{} -- Processing message: {}", requestTopicName, message.value());
        ResponseTopicMessage response = MESSAGE_PROCESSOR.processMessage(message.key(), message.value());
        log.info("{} -- Sending response: {}", responseTopicName, response);
        
        return response;
    }

}

package by.bsuir.discussionservice.kafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import by.bsuir.discussionservice.dto.response.MessageResponseTo;
import by.bsuir.discussionservice.exception.EntityNotFoundException;
import by.bsuir.discussionservice.exception.EntityNotSavedException;
import by.bsuir.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.discussionservice.kafka.message.ResponseTopicMessage;
import by.bsuir.discussionservice.service.MessageService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestTopicMessageProcessor {

    @Value("${topic.news-message.response}")
    private String responseTopic;

    private final MessageService MESSAGE_SERVICE;

    public ResponseTopicMessage processMessage(String key, RequestTopicMessage message) {
        switch (message.operation()) {
            case GET_BY_ID:
                return processGetById(key, message.requestMessage().id());
            case GET_ALL:
                return processGetAll(key, message.pageNumber(), message.pageSize());
            case SAVE:
                return processSave(key, message.requestMessage());
            case UPDATE:
                return processUpdate(key, message.requestMessage());
            case DELETE:
                return processDelete(key, message.requestMessage().id());
            default:
                throw new RuntimeException("Incorrect service operation");  
        }
    }

    private ResponseTopicMessage processGetById(String key, Long messageId) {
        try {
            MessageResponseTo message = MESSAGE_SERVICE.getById(messageId);
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .response(List.of(message))
                    .build();
            return responseTopicMessage;
        }
        catch (EntityNotFoundException e) {
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .error(e.getMessage())
                    .build();
            return responseTopicMessage;
        } 
    }

    private ResponseTopicMessage processGetAll(String key, Integer pageNumber, Integer pageSize) {
        List<MessageResponseTo> messages = MESSAGE_SERVICE.getAll(PageRequest.of(pageNumber, pageSize));
        ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                .response(messages)
                .build();
        return responseTopicMessage;
    }

    private ResponseTopicMessage processSave(String key, MessageRequestTo requestMessage) {
        try {
            MessageResponseTo savedMessage = MESSAGE_SERVICE.save(requestMessage);
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .response(List.of(savedMessage))
                    .build();
            return responseTopicMessage;
        }
        catch (EntityNotSavedException e) {
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .error(e.getMessage())
                    .build();
            return responseTopicMessage;
        } 
    }

    private ResponseTopicMessage processUpdate(String key, MessageRequestTo requestMessage) {
        try {
            MessageResponseTo updatedMessage = MESSAGE_SERVICE.update(requestMessage);
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .response(List.of(updatedMessage))
                    .build();
            return responseTopicMessage;
        }
        catch (EntityNotFoundException e) {
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .error(e.getMessage())
                    .build();
            return responseTopicMessage;
        } 
    }

    private ResponseTopicMessage processDelete(String key, Long messageId) {
        try {
            MESSAGE_SERVICE.delete(messageId);
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder().build();
            return responseTopicMessage;
        }
        catch (EntityNotFoundException e) {
            ResponseTopicMessage responseTopicMessage = ResponseTopicMessage.builder()
                    .error(e.getMessage())
                    .build();
            return responseTopicMessage;
        }
    }
}

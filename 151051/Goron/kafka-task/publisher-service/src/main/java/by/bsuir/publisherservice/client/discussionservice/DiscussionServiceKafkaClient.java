package by.bsuir.publisherservice.client.discussionservice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.DiscussionServiceOperation;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.RequestTopicMessage;
import by.bsuir.publisherservice.client.discussionservice.kafka.message.ResponseTopicMessage;
import by.bsuir.publisherservice.client.discussionservice.kafka.service.KafkaService;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import by.bsuir.publisherservice.exception.DiscussionServiceIncorrectRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscussionServiceKafkaClient implements DiscussionServiceClient {
    private final KafkaService KAFKA_SERVICE;

    @Override
    public MessageResponseTo getMessageById(Long id) {
        return Optional.of(
            KAFKA_SERVICE.sendAndRecieve(
                RequestTopicMessage.builder()
                        .operation(DiscussionServiceOperation.GET_BY_ID)
                        .requestMessage(
                            DiscussionServiceMessageRequestTo.builder()
                                    .id(id)
                                    .build()
                        )
                        .build()
            )
        ).filter(ResponseTopicMessage::isSuccessful)
                .map(ResponseTopicMessage::response)
                .map(List::getFirst)
                .orElseThrow(() -> 
                        new DiscussionServiceIncorrectRequestException("Message with id " + id + " doesn't exist"));
    }

    @Override
    public List<MessageResponseTo> getAllMessages(Integer pageNumber, Integer pageSize) {
        return Optional.of(
            KAFKA_SERVICE.sendAndRecieve(
                RequestTopicMessage.builder()
                        .operation(DiscussionServiceOperation.GET_ALL)
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()
            )
        ).filter(ResponseTopicMessage::isSuccessful)
                .map(ResponseTopicMessage::response)
                .orElseThrow(() -> new DiscussionServiceIncorrectRequestException());
    }

    @Override
    public MessageResponseTo saveMessage(@Valid DiscussionServiceMessageRequestTo message) {
        return Optional.of(
            KAFKA_SERVICE.sendAndRecieve(
                RequestTopicMessage.builder()
                        .operation(DiscussionServiceOperation.SAVE)
                        .requestMessage(message)
                        .build()
            )
        ).filter(ResponseTopicMessage::isSuccessful)
                .map(ResponseTopicMessage::response)
                .map(List::getFirst)
                .orElseThrow(() -> new DiscussionServiceIncorrectRequestException());
    }

    @Override
    public MessageResponseTo updateMessage(@Valid DiscussionServiceMessageRequestTo message) {
        return Optional.of(
            KAFKA_SERVICE.sendAndRecieve(
                RequestTopicMessage.builder()
                        .operation(DiscussionServiceOperation.UPDATE)
                        .requestMessage(message)
                        .build()
            )
        ).filter(ResponseTopicMessage::isSuccessful)
                .map(ResponseTopicMessage::response)
                .map(List::getFirst)
                .orElseThrow(() -> new DiscussionServiceIncorrectRequestException());
    }

    @Override
    public void deleteMessage(Long id) {
        Optional.of(
            KAFKA_SERVICE.sendAndRecieve(
                RequestTopicMessage.builder()
                        .operation(DiscussionServiceOperation.DELETE)
                        .requestMessage(
                            DiscussionServiceMessageRequestTo.builder()
                                    .id(id)
                                    .build()
                        )
                        .build()
            )
        ).filter(ResponseTopicMessage::isSuccessful)
                .orElseThrow(() -> new DiscussionServiceIncorrectRequestException());
    }

}

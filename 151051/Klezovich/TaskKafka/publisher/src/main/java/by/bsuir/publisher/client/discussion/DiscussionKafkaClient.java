package by.bsuir.publisher.client.discussion;

import by.bsuir.publisher.client.discussion.kafka.dto.KafkaRequestTo;
import by.bsuir.publisher.client.discussion.kafka.dto.KafkaResponseTo;
import by.bsuir.publisher.client.discussion.kafka.dto.OperationType;
import by.bsuir.publisher.client.discussion.kafka.service.KafkaService;
import by.bsuir.publisher.client.discussion.model.request.DiscussionRequestTo;
import by.bsuir.publisher.exception.IncorrectRequestException;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscussionKafkaClient implements DiscussionClient {

    private final KafkaService service;

    @Override
    public NoteResponseTo save(@Valid DiscussionRequestTo entity) {
        return Optional.of(service.sendAndReceive(
                KafkaRequestTo.builder()
                        .operation(OperationType.SAVE)
                        .noteRequestTo(entity)
                        .build()))
                .filter(KafkaResponseTo::isSuccessful)
                .map(KafkaResponseTo::response)
                .map(List::getFirst)
                .orElseThrow(IncorrectRequestException::new);
    }

    @Override
    public List<NoteResponseTo> findAll(Integer pageNumber, Integer pageSize) {
        return Optional.of(service.sendAndReceive(
                KafkaRequestTo.builder()
                        .operation(OperationType.FIND_ALL)
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()))
                .filter(KafkaResponseTo::isSuccessful)
                .map(KafkaResponseTo::response)
                .orElseThrow(IncorrectRequestException::new);
    }

    @Override
    public NoteResponseTo findById(Long id) {
        return Optional.of(service.sendAndReceive(
                        KafkaRequestTo.builder()
                                .operation(OperationType.FIND_BY_ID)
                                .noteRequestTo(DiscussionRequestTo.builder().id(id).build())
                                .build()))
                .filter(KafkaResponseTo::isSuccessful)
                .map(KafkaResponseTo::response)
                .map(List::getFirst)
                .orElseThrow(() -> new IncorrectRequestException("Note with id " + id + " doesn't exist"));
    }

    @Override
    public NoteResponseTo update(@Valid DiscussionRequestTo entity) {
        return Optional.of(service.sendAndReceive(
                        KafkaRequestTo.builder()
                                .operation(OperationType.UPDATE)
                                .noteRequestTo(entity)
                                .build()))
                .filter(KafkaResponseTo::isSuccessful)
                .map(KafkaResponseTo::response)
                .map(List::getFirst)
                .orElseThrow(IncorrectRequestException::new);
    }

    @Override
    public void delete(Long id) {
        Optional.of(service.sendAndReceive(
                        KafkaRequestTo.builder()
                                .operation(OperationType.DELETE)
                                .noteRequestTo(DiscussionRequestTo.builder().id(id).build())
                                .build()))
                .filter(KafkaResponseTo::isSuccessful)
                .orElseThrow(() -> new IncorrectRequestException("Note with id " + id + " doesn't exist"));
    }
}

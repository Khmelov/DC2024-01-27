package by.bsuir.discussionservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import by.bsuir.discussionservice.dto.response.MessageResponseTo;
import by.bsuir.discussionservice.entity.Message;
import by.bsuir.discussionservice.exception.EntityNotFoundException;
import by.bsuir.discussionservice.exception.EntityNotSavedException;
import by.bsuir.discussionservice.mapper.MessageMapper;
import by.bsuir.discussionservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper MESSAGE_MAPPER;
    private final MessageRepository MESSAGE_REPOSITORY;
    private final MessageModerator MESSAGE_MODERATOR;

    public List<MessageResponseTo> getAll(Pageable restriction) {
        return MESSAGE_REPOSITORY.findAll(restriction)
                                 .stream()
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .toList();
    }

    public MessageResponseTo getById(Long id) {
        return MESSAGE_REPOSITORY.findByKey_Id(id)
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .orElseThrow(() -> 
                                     new EntityNotFoundException("Message", id));    
    }

    public MessageResponseTo save(MessageRequestTo message) {
        return Optional.of(message)
                       .map(this::createMessageEntityWithGeneratedId)
                       .map(MESSAGE_MODERATOR::moderateMessage)
                       .map(MESSAGE_REPOSITORY::save)
                       .map(MESSAGE_MAPPER::toResponseTo)
                       .orElseThrow(() -> 
                           new EntityNotSavedException("Message", message.id()));
    }

    public MessageResponseTo update(MessageRequestTo message) {
        return MESSAGE_REPOSITORY.findByKey_Id(message.id())
                                 .map(entityToUpdate -> MESSAGE_MAPPER.updateEntity(entityToUpdate, message))
                                 .map(MESSAGE_MODERATOR::moderateMessage)
                                 .map(MESSAGE_REPOSITORY::save)
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .orElseThrow(() -> 
                                     new EntityNotFoundException("Message", message.id())); 
    }

    public void delete(Long id) {
        MESSAGE_REPOSITORY.findByKey_Id(id)
                          .ifPresentOrElse(MESSAGE_REPOSITORY::delete,
                                           () -> { 
                                               throw new EntityNotFoundException("Message", id); 
                                           });  
    }

    private Message createMessageEntityWithGeneratedId(MessageRequestTo message) {
        UUID uuid = UUID.randomUUID();

        Message messageEntity = MESSAGE_MAPPER.toEntity(message);
        messageEntity.getKey().setId(Math.abs(uuid.getMostSignificantBits()
                                              ^ uuid.getLeastSignificantBits()));

        return messageEntity;
    }
}

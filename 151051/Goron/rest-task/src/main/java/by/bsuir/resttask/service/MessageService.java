package by.bsuir.resttask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import by.bsuir.resttask.dto.request.MessageRequestTo;
import by.bsuir.resttask.dto.response.MessageResponseTo;
import by.bsuir.resttask.exception.EntityNotFoundException;
import by.bsuir.resttask.exception.EntityNotSavedException;
import by.bsuir.resttask.mapper.MessageMapper;
import by.bsuir.resttask.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper MESSAGE_MAPPER;
    private final MessageRepository MESSAGE_REPOSITORY;
    
    private final NewsService NEWS_SERVICE;

    public List<MessageResponseTo> getAll() {
        return MESSAGE_REPOSITORY.findAll()
                                 .stream()
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .toList();
    };

    public MessageResponseTo getById(Long id) {
        return MESSAGE_REPOSITORY.findById(id)
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .orElseThrow(() -> 
                                     new EntityNotFoundException("Message", id));
    };

    public MessageResponseTo save(MessageRequestTo message) {
        checkNewsExistence(message.newsId());
        return Optional.of(message)
                       .map(MESSAGE_MAPPER::toEntity)
                       .map(MESSAGE_REPOSITORY::save)
                       .map(MESSAGE_MAPPER::toResponseTo)
                       .orElseThrow(() -> 
                           new EntityNotSavedException("Message", message.id()));
    };

    public MessageResponseTo update(MessageRequestTo message) {
        checkNewsExistence(message.newsId());
        return MESSAGE_REPOSITORY.findById(message.id())
                                 .map(entityToUpdate -> MESSAGE_MAPPER.updateEntity(entityToUpdate, message))
                                 .map(MESSAGE_REPOSITORY::save)
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .orElseThrow(() -> 
                                     new EntityNotFoundException("Message", message.id()));
    };

    public void delete(Long id) {
        MESSAGE_REPOSITORY.findById(id)
                          .ifPresentOrElse(MESSAGE_REPOSITORY::delete,
                                           () -> { 
                                               throw new EntityNotFoundException("Message", id); 
                                           });  
    };

    private void checkNewsExistence(Long newsId) {
        NEWS_SERVICE.getById(newsId);
    }
}

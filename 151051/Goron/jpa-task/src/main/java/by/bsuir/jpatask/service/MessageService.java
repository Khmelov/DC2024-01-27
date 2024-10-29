package by.bsuir.jpatask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.jpatask.dto.request.MessageRequestTo;
import by.bsuir.jpatask.dto.response.MessageResponseTo;
import by.bsuir.jpatask.entity.News;
import by.bsuir.jpatask.exception.EntityNotFoundException;
import by.bsuir.jpatask.exception.EntityNotSavedException;
import by.bsuir.jpatask.mapper.MessageMapper;
import by.bsuir.jpatask.repository.MessageRepository;
import by.bsuir.jpatask.repository.NewsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper MESSAGE_MAPPER;
    private final MessageRepository MESSAGE_REPOSITORY;
    private final NewsRepository NEWS_REPOSITORY;

    public List<MessageResponseTo> getAll(Pageable restriction) {
        return MESSAGE_REPOSITORY.findAll(restriction)
                                 .stream()
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .toList();
    }

    public MessageResponseTo getById(Long id) {
        return MESSAGE_REPOSITORY.findById(id)
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .orElseThrow(() -> 
                                     new EntityNotFoundException("Message", id));    
    }

    public MessageResponseTo save(MessageRequestTo message) {
        News newsFromRequest = NEWS_REPOSITORY.findById(message.newsId())
                                   .orElseThrow(() -> 
                                       new EntityNotFoundException("News", message.newsId()));
        return Optional.of(message)
                       .map(request -> MESSAGE_MAPPER.toEntity(request, newsFromRequest))
                       .map(MESSAGE_REPOSITORY::save)
                       .map(MESSAGE_MAPPER::toResponseTo)
                       .orElseThrow(() -> 
                           new EntityNotSavedException("Message", message.id()));
    }

    public MessageResponseTo update(MessageRequestTo message) {
        News newsFromRequest = NEWS_REPOSITORY.findById(message.newsId())
                                              .orElseThrow(() -> 
                                                 new EntityNotFoundException("News", message.newsId()));
        return MESSAGE_REPOSITORY.findById(message.id())
                                 .map(entityToUpdate -> MESSAGE_MAPPER.updateEntity(entityToUpdate, message, newsFromRequest))
                                 .map(MESSAGE_REPOSITORY::save)
                                 .map(MESSAGE_MAPPER::toResponseTo)
                                 .orElseThrow(() -> 
                                     new EntityNotFoundException("Message", message.id()));
    }

    public void delete(Long id) {
        MESSAGE_REPOSITORY.findById(id)
                          .ifPresentOrElse(MESSAGE_REPOSITORY::delete,
                                           () -> { 
                                               throw new EntityNotFoundException("Message", id); 
                                           });  
    }
}

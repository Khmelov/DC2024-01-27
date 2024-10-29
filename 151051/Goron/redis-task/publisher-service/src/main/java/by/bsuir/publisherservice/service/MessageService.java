package by.bsuir.publisherservice.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.publisherservice.client.discussionservice.DiscussionServiceClient;
import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import by.bsuir.publisherservice.client.discussionservice.mapper.DiscussionServiceMessageMapper;
import by.bsuir.publisherservice.dto.request.MessageRequestTo;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import by.bsuir.publisherservice.exception.DiscussionServiceIncorrectRequestException;
import by.bsuir.publisherservice.exception.EntityNotFoundException;
import by.bsuir.publisherservice.exception.EntityNotSavedException;
import by.bsuir.publisherservice.repository.NewsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "message")
public class MessageService {

    private final DiscussionServiceMessageMapper DISCUSSION_SERVICE_MAPPER;
    private final DiscussionServiceClient DISCUSSION_SERVICE;
    private final NewsRepository NEWS_REPOSITORY;

    public List<MessageResponseTo> getAll(Pageable restriction) {
        return DISCUSSION_SERVICE.getAllMessages(restriction.getPageNumber(),
                                                 restriction.getPageSize());
    }

    @Cacheable(key = "#id")
    public MessageResponseTo getById(Long id) {
        try {
            return DISCUSSION_SERVICE.getMessageById(id);  
        } 
        catch (DiscussionServiceIncorrectRequestException e) {
            throw new EntityNotFoundException("Message", id);
        }  
    }

    public MessageResponseTo save(MessageRequestTo message, String country) {
        checkIfNewsExist(message.newsId());
        DiscussionServiceMessageRequestTo request = 
                DISCUSSION_SERVICE_MAPPER.toDiscussionServiceRequestTo(message, country);

        try {
            return DISCUSSION_SERVICE.saveMessage(request);
        }
        catch (DiscussionServiceIncorrectRequestException e) {
            throw new EntityNotSavedException("Message", message.id());
        }
    }

    @CachePut(key = "#message.id")
    public MessageResponseTo update(MessageRequestTo message, String country) {
        checkIfMessageExists(message.id());
        checkIfNewsExist(message.newsId());
        DiscussionServiceMessageRequestTo request = 
                DISCUSSION_SERVICE_MAPPER.toDiscussionServiceRequestTo(message, country);
        try {
            return DISCUSSION_SERVICE.updateMessage(request);
        }
        catch (DiscussionServiceIncorrectRequestException e) {
            throw new EntityNotSavedException("Message", message.id());
        }
    }

    @CacheEvict(key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        checkIfMessageExists(id);
        DISCUSSION_SERVICE.deleteMessage(id);
    }

    private void checkIfMessageExists(Long id) {
        getById(id);
    }

    private void checkIfNewsExist(Long id) {
        if (!NEWS_REPOSITORY.existsById(id)) {
            throw new EntityNotFoundException("News", id);
        }
    }
    
}

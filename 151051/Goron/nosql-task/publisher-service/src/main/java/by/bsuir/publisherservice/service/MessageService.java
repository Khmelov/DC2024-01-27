package by.bsuir.publisherservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import by.bsuir.publisherservice.client.discussionservice.DiscussionServiceClient;
import by.bsuir.publisherservice.client.discussionservice.mapper.DiscussionServiceMessageMapper;
import by.bsuir.publisherservice.dto.request.MessageRequestTo;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import by.bsuir.publisherservice.exception.EntityNotFoundException;
import by.bsuir.publisherservice.exception.EntityNotSavedException;
import by.bsuir.publisherservice.repository.NewsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final DiscussionServiceMessageMapper DISCUSSION_SERVICE_MAPPER;
    private final DiscussionServiceClient DISCUSSION_SERVICE;
    private final NewsRepository NEWS_REPOSITORY;

    public List<MessageResponseTo> getAll(Pageable restriction) {
        return DISCUSSION_SERVICE.getAllMessages(restriction.getPageNumber(), restriction.getPageSize());
    }

    public MessageResponseTo getById(Long id) {
        try {
            return DISCUSSION_SERVICE.getMessageById(id);  
        } 
        catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("Message", id);
        }     
    }

    public MessageResponseTo save(MessageRequestTo message, String country) {
        if (!NEWS_REPOSITORY.existsById(message.newsId())) {
            throw new EntityNotFoundException("News", message.newsId());
        }

        return Optional.of(message)
                       .map(messageRequest -> DISCUSSION_SERVICE_MAPPER
                                             .toDiscussionServiceRequestTo(messageRequest, country))
                       .map(DISCUSSION_SERVICE::saveMessage)
                       .orElseThrow(() -> new EntityNotSavedException("Message", message.id()));
    }

    public MessageResponseTo update(MessageRequestTo message, String country) {
        checkIfMessageExists(message.id());
        if (!NEWS_REPOSITORY.existsById(message.newsId())) {
            throw new EntityNotFoundException("News", message.newsId());
        }

        return Optional.of(message)
                       .map(updateRequest -> DISCUSSION_SERVICE_MAPPER
                                             .toDiscussionServiceRequestTo(updateRequest, country))
                       .map(DISCUSSION_SERVICE::updateMessage)
                       .orElseThrow(() -> new EntityNotFoundException("Message", message.id()));
    }

    public void delete(Long id) {
        checkIfMessageExists(id);
        DISCUSSION_SERVICE.deleteMessage(id);
    }

    private void checkIfMessageExists(Long id) {
        getById(id);
    }
    
}

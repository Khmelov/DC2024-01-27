package by.bsuir.publisher.service.impl;

import by.bsuir.publisher.client.discussion.DiscussionClient;
import by.bsuir.publisher.client.discussion.model.mapper.DiscussionMapper;
import by.bsuir.publisher.exception.EntityNotFoundException;
import by.bsuir.publisher.exception.EntitySavingException;
import by.bsuir.publisher.exception.IncorrectRequestException;
import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import by.bsuir.publisher.repository.TweetRepository;
import by.bsuir.publisher.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "note")
public class NoteServiceImpl implements NoteService {

    private final DiscussionClient repository;
    private final TweetRepository tweetRepository;
    private final DiscussionMapper mapper;
    private final String entityName = "Note";

    @Override
    public NoteResponseTo save(NoteRequestTo noteRequestTo, String country) {
        if (!tweetRepository.existsById(noteRequestTo.tweetId())) {
            throw new EntityNotFoundException(entityName, noteRequestTo.tweetId());
        }

        try {
            return repository.save(mapper.toRequestTo(noteRequestTo, country));
        }
        catch (IncorrectRequestException e) {
            throw new EntitySavingException(entityName, noteRequestTo.id());
        }
    }

    @Override
    public List<NoteResponseTo> findAll(Pageable restriction) {
        return repository.findAll(restriction.getPageNumber(), restriction.getPageSize());
    }

    @Override
    @Cacheable(key = "#id")
    public NoteResponseTo findById(Long id) {
        try {
            return repository.findById(id);
        } catch (IncorrectRequestException e) {
            throw new EntityNotFoundException(entityName, id);
        }
    }

    @Override
    @CachePut(key = "#noteRequestTo.id")
    public NoteResponseTo update(NoteRequestTo noteRequestTo, String country) {
        findById(noteRequestTo.id());
        if (!tweetRepository.existsById(noteRequestTo.tweetId())) {
            throw new EntityNotFoundException(entityName, noteRequestTo.tweetId());
        }

        try {
            return repository.update(mapper.toRequestTo(noteRequestTo, country));
        }
        catch (IncorrectRequestException e) {
            throw new EntitySavingException(entityName, noteRequestTo.id());
        }
    }

    @Override
    @CacheEvict(key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        findById(id);
        repository.delete(id);
    }
}

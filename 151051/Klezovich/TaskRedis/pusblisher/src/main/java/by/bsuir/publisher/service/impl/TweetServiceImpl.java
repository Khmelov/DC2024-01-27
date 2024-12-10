package by.bsuir.publisher.service.impl;

import by.bsuir.publisher.exception.EntityNotFoundException;
import by.bsuir.publisher.exception.EntitySavingException;
import by.bsuir.publisher.model.dto.request.TweetRequestTo;
import by.bsuir.publisher.model.dto.response.TweetResponseTo;
import by.bsuir.publisher.model.entity.User;
import by.bsuir.publisher.model.mapper.TweetMapper;
import by.bsuir.publisher.repository.TweetRepository;
import by.bsuir.publisher.repository.UserRepository;
import by.bsuir.publisher.service.TweetService;
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
@CacheConfig(cacheNames = "tweet")
public class TweetServiceImpl implements TweetService {

    private final TweetRepository repository;
    private final UserRepository userRepository;
    private final TweetMapper mapper;
    private final String entityName = "Tweet";

    @Override
    public TweetResponseTo save(TweetRequestTo tweetRequestTo) {
        User userFromRequest = userRepository.findById(tweetRequestTo.userId())
                .orElseThrow(() ->
                        new EntityNotFoundException(entityName, tweetRequestTo.userId()));
        return Optional.of(tweetRequestTo)
                .map(request -> mapper.toEntity(request, userFromRequest))
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntitySavingException(entityName, tweetRequestTo.id()));
    }

    @Override
    public List<TweetResponseTo> findAll(Pageable restriction) {
        return repository.findAll(restriction).stream().map(mapper::toResponseTo).toList();
    }

    @Override
    @Cacheable(key = "#id")
    public TweetResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    @CachePut(key = "#tweetRequestTo.id")
    public TweetResponseTo update(TweetRequestTo tweetRequestTo) {
        User userFromRequest = userRepository.findById(tweetRequestTo.userId())
                .orElseThrow(() ->
                        new EntityNotFoundException(entityName, tweetRequestTo.userId()));
        return repository.findById(tweetRequestTo.id())
                .map(entityToUpdate -> mapper.updateEntity(entityToUpdate, tweetRequestTo, userFromRequest))
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", tweetRequestTo.id())));
    }

    @Override
    @CacheEvict(key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

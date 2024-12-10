package by.bsuir.taskrest.service.impl;

import by.bsuir.taskrest.exception.EntityNotFoundException;
import by.bsuir.taskrest.exception.EntitySavingException;
import by.bsuir.taskrest.model.dto.request.TweetRequestTo;
import by.bsuir.taskrest.model.dto.response.TweetResponseTo;
import by.bsuir.taskrest.model.mapper.TweetMapper;
import by.bsuir.taskrest.repository.impl.TweetRepository;
import by.bsuir.taskrest.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository repository;
    private final TweetMapper mapper;
    private final String entityName = "Tweet";

    @Override
    public TweetResponseTo save(TweetRequestTo tweetRequestTo) {
        return Optional.of(tweetRequestTo)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntitySavingException(entityName, tweetRequestTo.id()));
    }

    @Override
    public List<TweetResponseTo> findAll() {
        return repository.findAll().stream().map(mapper::toResponseTo).toList();
    }

    @Override
    public TweetResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    public TweetResponseTo update(TweetRequestTo tweetRequestTo) {
        return repository.findById(tweetRequestTo.id())
                .map(entity -> {
                    if (tweetRequestTo.userId() != null) {
                        entity.setUserId(tweetRequestTo.userId());
                    }
                    entity.setTitle(tweetRequestTo.title());
                    entity.setContent(tweetRequestTo.content());
                    entity.setModified(LocalDateTime.now());
                    return entity;
                })
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", tweetRequestTo.id())));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

package by.bsuir.taskjpa.service.impl;

import by.bsuir.taskjpa.exception.EntityNotFoundException;
import by.bsuir.taskjpa.exception.EntitySavingException;
import by.bsuir.taskjpa.model.dto.request.TweetRequestTo;
import by.bsuir.taskjpa.model.dto.response.TweetResponseTo;
import by.bsuir.taskjpa.model.entity.User;
import by.bsuir.taskjpa.model.mapper.TweetMapper;
import by.bsuir.taskjpa.repository.TweetRepository;
import by.bsuir.taskjpa.repository.UserRepository;
import by.bsuir.taskjpa.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
    public TweetResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
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
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

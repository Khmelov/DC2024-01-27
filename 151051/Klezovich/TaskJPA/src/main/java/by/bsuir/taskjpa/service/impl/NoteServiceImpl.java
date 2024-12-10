package by.bsuir.taskjpa.service.impl;

import by.bsuir.taskjpa.exception.EntityNotFoundException;
import by.bsuir.taskjpa.exception.EntitySavingException;
import by.bsuir.taskjpa.model.dto.request.NoteRequestTo;
import by.bsuir.taskjpa.model.dto.response.NoteResponseTo;
import by.bsuir.taskjpa.model.entity.Tweet;
import by.bsuir.taskjpa.model.mapper.NoteMapper;
import by.bsuir.taskjpa.repository.NoteRepository;
import by.bsuir.taskjpa.repository.TweetRepository;
import by.bsuir.taskjpa.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final TweetRepository tweetRepository;
    private final NoteMapper mapper;
    private final String entityName = "Note";

    @Override
    public NoteResponseTo save(NoteRequestTo noteRequestTo) {
        Tweet tweetFromRequest = tweetRepository.findById(noteRequestTo.tweetId())
                .orElseThrow(() ->
                        new EntityNotFoundException(entityName, noteRequestTo.tweetId()));
        return Optional.of(noteRequestTo)
                .map(request -> mapper.toEntity(request, tweetFromRequest))
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntitySavingException(entityName, noteRequestTo.id()));
    }

    @Override
    public List<NoteResponseTo> findAll(Pageable restriction) {
        return repository.findAll(restriction).stream().map(mapper::toResponseTo).toList();
    }

    @Override
    public NoteResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    public NoteResponseTo update(NoteRequestTo noteRequestTo) {
        Tweet tweetFromRequest = tweetRepository.findById(noteRequestTo.tweetId())
                .orElseThrow(() ->
                        new EntityNotFoundException(entityName, noteRequestTo.tweetId()));
        return repository.findById(noteRequestTo.id())
                .map(entityToUpdate -> mapper.updateEntity(entityToUpdate, noteRequestTo, tweetFromRequest))
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", noteRequestTo.id())));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

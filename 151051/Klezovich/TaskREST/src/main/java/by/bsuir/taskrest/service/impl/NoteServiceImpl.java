package by.bsuir.taskrest.service.impl;

import by.bsuir.taskrest.exception.EntityNotFoundException;
import by.bsuir.taskrest.exception.EntitySavingException;
import by.bsuir.taskrest.model.dto.request.NoteRequestTo;
import by.bsuir.taskrest.model.dto.response.NoteResponseTo;
import by.bsuir.taskrest.model.mapper.NoteMapper;
import by.bsuir.taskrest.repository.impl.NoteRepository;
import by.bsuir.taskrest.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final NoteMapper mapper;
    private final String entityName = "Note";

    @Override
    public NoteResponseTo save(NoteRequestTo noteRequestTo) {
        return Optional.of(noteRequestTo)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntitySavingException(entityName, noteRequestTo.id()));
    }

    @Override
    public List<NoteResponseTo> findAll() {
        return repository.findAll().stream().map(mapper::toResponseTo).toList();
    }

    @Override
    public NoteResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    public NoteResponseTo update(NoteRequestTo noteRequestTo) {
        return repository.findById(noteRequestTo.id())
                .map(entity -> {
                    if (noteRequestTo.tweetId() != null) {
                        entity.setTweetId(noteRequestTo.tweetId());
                    }
                    if (noteRequestTo.content() != null) {
                        entity.setContent(noteRequestTo.content());
                    }
                    return entity;
                })
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

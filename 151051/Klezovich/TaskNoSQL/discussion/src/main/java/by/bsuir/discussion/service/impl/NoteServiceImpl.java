package by.bsuir.discussion.service.impl;

import by.bsuir.discussion.exception.EntityNotFoundException;
import by.bsuir.discussion.exception.EntitySavingException;
import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import by.bsuir.discussion.model.dto.response.NoteResponseTo;
import by.bsuir.discussion.model.entity.Note;
import by.bsuir.discussion.model.mapper.NoteMapper;
import by.bsuir.discussion.repository.NoteRepository;
import by.bsuir.discussion.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final NoteMapper mapper;
    private final String entityName = "Note";

    @Override
    public NoteResponseTo save(NoteRequestTo noteRequestTo) {
        return Optional.of(noteRequestTo)
                .map(this::generateNoteEntity)
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
        return repository.findByKeyId(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    public NoteResponseTo update(NoteRequestTo noteRequestTo) {
        return repository.findByKeyId(noteRequestTo.id())
                .map(entityToUpdate -> mapper.updateEntity(entityToUpdate, noteRequestTo))
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", noteRequestTo.id())));
    }

    @Override
    public void delete(Long id) {
        repository.findByKeyId(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }

    private Note generateNoteEntity(NoteRequestTo requestTo) {
        Note entity = mapper.toEntity(requestTo);
        entity.getKey().setId(getAbs());
        return entity;
    }

    private static long getAbs() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits());
    }
}

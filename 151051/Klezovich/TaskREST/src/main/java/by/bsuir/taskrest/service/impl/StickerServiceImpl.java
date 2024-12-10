package by.bsuir.taskrest.service.impl;

import by.bsuir.taskrest.exception.EntityNotFoundException;
import by.bsuir.taskrest.exception.EntitySavingException;
import by.bsuir.taskrest.model.dto.request.StickerRequestTo;
import by.bsuir.taskrest.model.dto.response.StickerResponseTo;
import by.bsuir.taskrest.model.mapper.StickerMapper;
import by.bsuir.taskrest.repository.impl.StickerRepository;
import by.bsuir.taskrest.service.StickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

    private final StickerRepository repository;
    private final StickerMapper mapper;
    private final String entityName = "Sticker";

    @Override
    public StickerResponseTo save(StickerRequestTo stickerRequestTo) {
        return Optional.of(stickerRequestTo)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntitySavingException(entityName, stickerRequestTo.id()));
    }

    @Override
    public List<StickerResponseTo> findAll() {
        return repository.findAll().stream().map(mapper::toResponseTo).toList();
    }

    @Override
    public StickerResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    public StickerResponseTo update(StickerRequestTo stickerRequestTo) {
        return repository.findById(stickerRequestTo.id())
                .map(entity -> {
                    if (stickerRequestTo.name() != null) {
                        entity.setName(stickerRequestTo.name());
                    }
                    return entity;
                })
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", stickerRequestTo.id())));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

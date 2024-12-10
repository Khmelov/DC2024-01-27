package by.bsuir.publisher.service.impl;

import by.bsuir.publisher.exception.EntityNotFoundException;
import by.bsuir.publisher.exception.EntitySavingException;
import by.bsuir.publisher.model.dto.request.StickerRequestTo;
import by.bsuir.publisher.model.dto.response.StickerResponseTo;
import by.bsuir.publisher.model.mapper.StickerMapper;
import by.bsuir.publisher.repository.StickerRepository;
import by.bsuir.publisher.service.StickerService;
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
@CacheConfig(cacheNames = "sticker")
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
    public List<StickerResponseTo> findAll(Pageable restriction) {
        return repository.findAll(restriction).stream().map(mapper::toResponseTo).toList();
    }

    @Override
    @Cacheable(key = "#id")
    public StickerResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    @CachePut(key = "#stickerRequestTo.id")
    public StickerResponseTo update(StickerRequestTo stickerRequestTo) {
        return repository.findById(stickerRequestTo.id())
                .map(entityToUpdate -> mapper.updateEntity(entityToUpdate, stickerRequestTo))
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", stickerRequestTo.id())));
    }

    @Override
    @CacheEvict(key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

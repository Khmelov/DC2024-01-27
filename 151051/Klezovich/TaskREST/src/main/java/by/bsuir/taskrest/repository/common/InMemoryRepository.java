package by.bsuir.taskrest.repository.common;

import by.bsuir.taskrest.model.entity.Entity;
import jakarta.annotation.Nonnull;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class InMemoryRepository<T extends Entity> implements CrudRepository<T, Long>  {

    private final static AtomicLong ids = new AtomicLong();

    protected final Map<Long, T> map = new HashMap<>();

    @Override
    public <S extends T> S save(@Nonnull S entity) {
        if (isNewEntity(entity)) {
            final Long entityId = ids.incrementAndGet();
            entity.setId(entityId);
        }

        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void delete(@Nonnull T entity) {
        map.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }

    private <S extends T> boolean isNewEntity(S entity) {
        return entity.getId() == null;
    }
}

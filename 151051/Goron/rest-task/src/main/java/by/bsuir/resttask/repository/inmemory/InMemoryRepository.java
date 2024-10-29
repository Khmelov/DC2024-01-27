package by.bsuir.resttask.repository.inmemory;

import by.bsuir.resttask.entity.Entity;
import by.bsuir.resttask.repository.Repository;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public abstract class InMemoryRepository<T extends Entity> implements Repository<T, Long> {
    
    private final AtomicLong ID_COUNTER = new AtomicLong();
    private final Map<Long, T> ENTITIES = new ConcurrentHashMap<>();
    
    @Override
    public void delete(@Nonnull T entity) {
        ENTITIES.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        ENTITIES.remove(id);
    }
    
    @Override
    public List<T> findAll() {
        return ENTITIES.values().stream().toList();
    }
    
    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(ENTITIES.get(id));
    }
    
    @Override
    public <S extends T> S save(@Nonnull S entity) {
        if (isNewEntity(entity)) {
            final Long entityId = ID_COUNTER.incrementAndGet();
            entity.setId(entityId);
        } 
        
        ENTITIES.put(entity.getId(), entity);
        return entity;
    }

    private <S extends T> boolean isNewEntity(S entity) {
        return entity.getId() == null;
    }

}

package by.bsuir.resttask.repository;

import java.util.List;
import java.util.Optional;

import jakarta.annotation.Nonnull;

public interface Repository<T, ID> {
    void delete(@Nonnull T entity);
    void deleteById(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);
    <S extends T> S save(@Nonnull S entity);
}

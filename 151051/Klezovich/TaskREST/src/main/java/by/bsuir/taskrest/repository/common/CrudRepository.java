package by.bsuir.taskrest.repository.common;

import jakarta.annotation.Nonnull;

import java.util.Optional;

public interface CrudRepository<T, ID>  {
    <S extends T> S save(@Nonnull S entity);
    Iterable<T> findAll();
    Optional<T> findById(ID id);
    void delete(@Nonnull T entity);
    void deleteById(ID id);
}

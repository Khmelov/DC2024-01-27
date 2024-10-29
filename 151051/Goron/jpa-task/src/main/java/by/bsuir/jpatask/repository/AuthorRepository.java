package by.bsuir.jpatask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.jpatask.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

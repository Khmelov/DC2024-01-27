package by.bsuir.publisherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.publisherservice.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

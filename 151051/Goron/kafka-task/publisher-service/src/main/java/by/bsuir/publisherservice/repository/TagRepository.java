package by.bsuir.publisherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.publisherservice.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}

package by.bsuir.publisherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.publisherservice.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}

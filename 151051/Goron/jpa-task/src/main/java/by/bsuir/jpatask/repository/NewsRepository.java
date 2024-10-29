package by.bsuir.jpatask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.jpatask.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}

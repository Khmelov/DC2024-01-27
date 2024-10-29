package by.bsuir.jpatask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.jpatask.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}

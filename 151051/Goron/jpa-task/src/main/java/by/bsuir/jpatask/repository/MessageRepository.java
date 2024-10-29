package by.bsuir.jpatask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.jpatask.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}

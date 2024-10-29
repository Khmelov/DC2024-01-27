package by.bsuir.discussionservice.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import by.bsuir.discussionservice.entity.Message;

@Repository
public interface MessageRepository extends CassandraRepository<Message, Message.Key> {
    @AllowFiltering
    Optional<Message> findByKey_Id(Long id);
}

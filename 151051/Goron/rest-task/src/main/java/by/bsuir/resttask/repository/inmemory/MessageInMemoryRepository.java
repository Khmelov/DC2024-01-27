package by.bsuir.resttask.repository.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import by.bsuir.resttask.entity.Message;
import by.bsuir.resttask.repository.MessageRepository;

@Repository
@Profile("in-memory-repositories")
public class MessageInMemoryRepository extends InMemoryRepository<Message>
                                       implements MessageRepository {
    
}

package by.bsuir.resttask.repository.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import by.bsuir.resttask.entity.Tag;
import by.bsuir.resttask.repository.TagRepository;


@Repository
@Profile("in-memory-repositories")
public class TagInMemoryRepository extends InMemoryRepository<Tag>
                                   implements TagRepository {
    
}

package by.bsuir.resttask.repository.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import by.bsuir.resttask.entity.News;
import by.bsuir.resttask.repository.NewsRepository;


@Repository
@Profile("in-memory-repositories")
public class NewsInMemoryRepository extends InMemoryRepository<News>
                                    implements NewsRepository {
    
}

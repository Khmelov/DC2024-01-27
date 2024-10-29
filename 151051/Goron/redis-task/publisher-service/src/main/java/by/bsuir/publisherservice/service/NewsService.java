package by.bsuir.publisherservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.publisherservice.dto.request.NewsRequestTo;
import by.bsuir.publisherservice.dto.response.NewsResponseTo;
import by.bsuir.publisherservice.entity.Author;
import by.bsuir.publisherservice.exception.EntityNotFoundException;
import by.bsuir.publisherservice.exception.EntityNotSavedException;
import by.bsuir.publisherservice.mapper.NewsMapper;
import by.bsuir.publisherservice.repository.AuthorRepository;
import by.bsuir.publisherservice.repository.NewsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "news")
public class NewsService {

    private final NewsMapper NEWS_MAPPER;
    private final NewsRepository NEWS_REPOSITORY;
    private final AuthorRepository AUTHOR_REPOSITORY;

    public List<NewsResponseTo> getAll(Pageable restriction) {
        return NEWS_REPOSITORY.findAll(restriction)
                              .stream()
                              .map(NEWS_MAPPER::toResponseTo)
                              .toList();
    }

    @Cacheable(key = "#id")
    public NewsResponseTo getById(Long id) {
        return NEWS_REPOSITORY.findById(id)
                              .map(NEWS_MAPPER::toResponseTo)
                              .orElseThrow(() -> 
                                  new EntityNotFoundException("News", id));
    }

    public NewsResponseTo save(NewsRequestTo news) {
        Author authorFromRequest = AUTHOR_REPOSITORY.findById(news.authorId())
                                                    .orElseThrow(() -> 
                                                        new EntityNotFoundException("Author", news.authorId()));
        return Optional.of(news)
                       .map(request -> NEWS_MAPPER.toEntity(request, authorFromRequest))
                       .map(NEWS_REPOSITORY::save)
                       .map(NEWS_MAPPER::toResponseTo)
                       .orElseThrow(() -> 
                           new EntityNotSavedException("News", news.id()));
    }

    @CachePut(key = "#news.id")
    public NewsResponseTo update(NewsRequestTo news) {
        Author authorFromRequest = AUTHOR_REPOSITORY.findById(news.authorId())
                                                    .orElseThrow(() -> 
                                                        new EntityNotFoundException("Author", news.authorId()));
        return NEWS_REPOSITORY.findById(news.id())
                              .map(entityToUpdate -> NEWS_MAPPER.updateEntity(entityToUpdate, news, authorFromRequest))
                              .map(NEWS_REPOSITORY::save)
                              .map(NEWS_MAPPER::toResponseTo)
                              .orElseThrow(() -> 
                                  new EntityNotFoundException("News", news.id()));
    }

    @CacheEvict(key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        NEWS_REPOSITORY.findById(id)
                       .ifPresentOrElse(NEWS_REPOSITORY::delete,
                                        () -> { 
                                            throw new EntityNotFoundException("News", id); 
                                        });  
    }

}

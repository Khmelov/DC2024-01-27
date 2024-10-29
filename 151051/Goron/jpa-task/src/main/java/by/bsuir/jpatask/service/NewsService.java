package by.bsuir.jpatask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.jpatask.dto.request.NewsRequestTo;
import by.bsuir.jpatask.dto.response.NewsResponseTo;
import by.bsuir.jpatask.entity.Author;
import by.bsuir.jpatask.exception.EntityNotFoundException;
import by.bsuir.jpatask.exception.EntityNotSavedException;
import by.bsuir.jpatask.mapper.NewsMapper;
import by.bsuir.jpatask.repository.AuthorRepository;
import by.bsuir.jpatask.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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

    public void delete(Long id) {
        NEWS_REPOSITORY.findById(id)
                       .ifPresentOrElse(NEWS_REPOSITORY::delete,
                                        () -> { 
                                            throw new EntityNotFoundException("News", id); 
                                        });  
    }

}

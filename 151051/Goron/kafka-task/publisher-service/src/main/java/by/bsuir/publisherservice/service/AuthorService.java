package by.bsuir.publisherservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.publisherservice.dto.request.AuthorRequestTo;
import by.bsuir.publisherservice.dto.response.AuthorResponseTo;
import by.bsuir.publisherservice.exception.EntityNotFoundException;
import by.bsuir.publisherservice.exception.EntityNotSavedException;
import by.bsuir.publisherservice.mapper.AuthorMapper;
import by.bsuir.publisherservice.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorMapper AUTHOR_MAPPER;
    private final AuthorRepository AUTHOR_REPOSITORY;

    public List<AuthorResponseTo> getAll(Pageable restriction) {
        return AUTHOR_REPOSITORY.findAll(restriction)
                                .stream()
                                .map(AUTHOR_MAPPER::toResponseTo)
                                .toList();
    }

    public AuthorResponseTo getById(Long id) {
        return AUTHOR_REPOSITORY.findById(id)
                                .map(AUTHOR_MAPPER::toResponseTo)
                                .orElseThrow(() -> 
                                    new EntityNotFoundException("Author", id));
    }

    public AuthorResponseTo save(AuthorRequestTo author) {
        return Optional.of(author)
                       .map(AUTHOR_MAPPER::toEntity)
                       .map(AUTHOR_REPOSITORY::save)
                       .map(AUTHOR_MAPPER::toResponseTo)
                       .orElseThrow(() -> 
                           new EntityNotSavedException("Author", author.id()));
    }

    public AuthorResponseTo update(AuthorRequestTo author) {
        return AUTHOR_REPOSITORY.findById(author.id())
                                .map(entityToUpdate -> AUTHOR_MAPPER.updateEntity(entityToUpdate, author))
                                .map(AUTHOR_REPOSITORY::save)
                                .map(AUTHOR_MAPPER::toResponseTo)
                                .orElseThrow(() -> 
                                    new EntityNotFoundException("Author", author.id()));
    }

    public void delete(Long id) {
        AUTHOR_REPOSITORY.findById(id)
                         .ifPresentOrElse(AUTHOR_REPOSITORY::delete,
                                          () -> { 
                                              throw new EntityNotFoundException("Author", id); 
                                          });                        
    }
    
}

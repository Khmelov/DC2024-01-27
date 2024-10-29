package by.bsuir.jpatask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.bsuir.jpatask.dto.request.TagRequestTo;
import by.bsuir.jpatask.dto.response.TagResponseTo;
import by.bsuir.jpatask.exception.EntityNotFoundException;
import by.bsuir.jpatask.exception.EntityNotSavedException;
import by.bsuir.jpatask.mapper.TagMapper;
import by.bsuir.jpatask.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagMapper TAG_MAPPER;
    private final TagRepository TAG_REPOSITORY;

    public List<TagResponseTo> getAll(Pageable restriction) {
        return TAG_REPOSITORY.findAll(restriction)
                             .stream()
                             .map(TAG_MAPPER::toResponseTo)
                             .toList();
    }

    public TagResponseTo getById(Long id) {
        return TAG_REPOSITORY.findById(id)
                             .map(TAG_MAPPER::toResponseTo)
                             .orElseThrow(() -> 
                                 new EntityNotFoundException("Tag", id));
    }

    public TagResponseTo save(TagRequestTo tag) {
        return Optional.of(tag)
                       .map(TAG_MAPPER::toEntity)
                       .map(TAG_REPOSITORY::save)
                       .map(TAG_MAPPER::toResponseTo)
                       .orElseThrow(() -> 
                           new EntityNotSavedException("Tag", tag.id()));
    }

    public TagResponseTo update(TagRequestTo tag) {
        return TAG_REPOSITORY.findById(tag.id())
                             .map(entityToUpdate -> TAG_MAPPER.updateEntity(entityToUpdate, tag))
                             .map(TAG_REPOSITORY::save)
                             .map(TAG_MAPPER::toResponseTo)
                             .orElseThrow(() -> 
                                 new EntityNotFoundException("Tag", tag.id()));
    }

    public void delete(Long id) {
        TAG_REPOSITORY.findById(id)
                      .ifPresentOrElse(TAG_REPOSITORY::delete,
                                       () -> { 
                                           throw new EntityNotFoundException("Tag", id); 
                                       });  
        
    }
}

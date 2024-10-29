package by.bsuir.publisherservice.mapper;

import by.bsuir.publisherservice.dto.request.AuthorRequestTo;
import by.bsuir.publisherservice.dto.response.AuthorResponseTo;
import by.bsuir.publisherservice.entity.Author;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    Author toEntity(AuthorRequestTo request);

    AuthorResponseTo toResponseTo(Author entity);
    
    @Mapping(target = "id", ignore = true)
    Author updateEntity(@MappingTarget Author entityToUpdate, AuthorRequestTo updateRequest);
}

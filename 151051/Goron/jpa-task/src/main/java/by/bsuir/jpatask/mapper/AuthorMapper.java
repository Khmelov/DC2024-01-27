package by.bsuir.jpatask.mapper;

import by.bsuir.jpatask.dto.request.AuthorRequestTo;
import by.bsuir.jpatask.dto.response.AuthorResponseTo;
import by.bsuir.jpatask.entity.Author;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestTo request);
    AuthorResponseTo toResponseTo(Author entity);
    Author updateEntity(@MappingTarget Author entityToUpdate, AuthorRequestTo updateRequest);
}

package by.bsuir.resttask.mapper;

import by.bsuir.resttask.dto.request.AuthorRequestTo;
import by.bsuir.resttask.dto.response.AuthorResponseTo;
import by.bsuir.resttask.entity.Author;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestTo request);
    AuthorResponseTo toResponseTo(Author entity);
    Author updateEntity(@MappingTarget Author entityToUpdate, AuthorRequestTo updateRequest);
}

package by.bsuir.publisher.dto.responses.converters;

import by.bsuir.publisher.domain.Author;
import by.bsuir.publisher.dto.responses.AuthorResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AuthorResponseConverter.class)
public interface CollectionAuthorResponseConverter {
    List<AuthorResponseDto> toListDto(List<Author> authors);
}
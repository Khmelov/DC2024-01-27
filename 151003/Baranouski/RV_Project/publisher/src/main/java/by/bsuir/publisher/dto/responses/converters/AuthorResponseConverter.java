package by.bsuir.publisher.dto.responses.converters;

import by.bsuir.publisher.domain.Author;
import by.bsuir.publisher.dto.responses.AuthorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorResponseConverter {
    AuthorResponseDto toDto(Author author);
}

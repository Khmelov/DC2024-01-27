package by.bsuir.publisher.dto.requests.converters;

import by.bsuir.publisher.domain.Author;
import by.bsuir.publisher.dto.requests.AuthorRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorRequestConverter {
    Author fromDto(AuthorRequestDto author);
}

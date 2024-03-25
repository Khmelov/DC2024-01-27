package by.bsuir.publisher.services;

import by.bsuir.publisher.dto.requests.AuthorRequestDto;
import by.bsuir.publisher.dto.responses.AuthorResponseDto;

import java.util.List;

public interface AuthorService extends BaseService<AuthorRequestDto, AuthorResponseDto> {
    List<AuthorResponseDto> readAll();
}

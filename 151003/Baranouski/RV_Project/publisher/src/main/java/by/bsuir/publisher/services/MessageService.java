package by.bsuir.publisher.services;

import by.bsuir.publisher.dto.requests.MessageRequestDto;
import by.bsuir.publisher.dto.responses.MessageResponseDto;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import jakarta.validation.Valid;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {
    MessageResponseDto create(@Valid @NonNull MessageRequestDto dto) throws EntityExistsException;
    Optional<MessageResponseDto> read(@NonNull UUID uuid);
    List<MessageResponseDto> readAll();
    MessageResponseDto update(@Valid @NonNull MessageRequestDto dto) throws NoEntityExistsException;
    UUID delete(@NonNull UUID uuid) throws NoEntityExistsException;
}

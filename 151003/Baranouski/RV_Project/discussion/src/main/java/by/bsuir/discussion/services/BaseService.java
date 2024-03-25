package by.bsuir.discussion.services;


import by.bsuir.discussion.dto.requests.BaseRequestDto;
import by.bsuir.discussion.dto.responses.BaseResponseDto;
import by.bsuir.discussion.exceptions.EntityExistsException;
import by.bsuir.discussion.exceptions.NoEntityExistsException;
import jakarta.validation.Valid;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface BaseService<T extends BaseRequestDto, V extends BaseResponseDto> {
    V create(@Valid @NonNull T dto) throws EntityExistsException;
    Optional<V> read(@NonNull UUID id);
    V update(@Valid @NonNull T dto) throws NoEntityExistsException;
    UUID delete(@NonNull UUID id) throws NoEntityExistsException;
}

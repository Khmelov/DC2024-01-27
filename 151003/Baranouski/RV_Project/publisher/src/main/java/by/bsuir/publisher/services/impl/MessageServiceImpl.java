package by.bsuir.publisher.services.impl;

import by.bsuir.publisher.dto.requests.MessageRequestDto;
import by.bsuir.publisher.dto.responses.MessageResponseDto;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.Messages;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import by.bsuir.publisher.services.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class MessageServiceImpl implements MessageService {
    private final RestTemplate restTemplate;
    private final static String CREATE_MESSAGE_URI = "http://localhost:24130/api/v1.0/messages";
    private final static String READ_MESSAGE_URI = "http://localhost:24130/api/v1.0/messages/";
    private final static String READ_ALL_MESSAGE_URI = "http://localhost:24130/api/v1.0/messages";
    private final static String UPDATE_MESSAGE_URI = "http://localhost:24130/api/v1.0/messages";
    private final static String DELETE_MESSAGE_URI = "http://localhost:24130/api/v1.0/messages/";
    @Override
    public MessageResponseDto create(@NonNull MessageRequestDto dto) throws EntityExistsException {
        try {
            return restTemplate.postForObject(CREATE_MESSAGE_URI, dto, MessageResponseDto.class);
        } catch (HttpClientErrorException e) {
            throw new EntityExistsException(Messages.EntityExistsException);
        }
    }

    @Override
    public Optional<MessageResponseDto> read(@NonNull UUID uuid) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(READ_MESSAGE_URI + uuid,
                    MessageResponseDto.class));
        } catch(HttpClientErrorException e) {
            return Optional.empty();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MessageResponseDto> readAll() {
        return restTemplate.getForObject(READ_ALL_MESSAGE_URI, List.class);
    }

    @Override
    public MessageResponseDto update(@NonNull MessageRequestDto dto) throws NoEntityExistsException {
        try {
            return restTemplate.exchange(UPDATE_MESSAGE_URI, HttpMethod.PUT,
                    new HttpEntity<>(dto), MessageResponseDto.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new NoEntityExistsException(Messages.NoEntityExistsException);
        }
    }

    @Override
    public UUID delete(@NonNull UUID uuid) throws NoEntityExistsException {
        try {
            return restTemplate.exchange(DELETE_MESSAGE_URI + uuid, HttpMethod.DELETE,
                    new HttpEntity<>(uuid), UUID.class).getBody();
        }
         catch (HttpClientErrorException e) {
            throw new NoEntityExistsException(Messages.NoEntityExistsException);
        }
    }
}

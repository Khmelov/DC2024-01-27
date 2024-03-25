package by.bsuir.discussion.services.impl;

import by.bsuir.discussion.domain.Message;
import by.bsuir.discussion.dto.requests.MessageRequestDto;
import by.bsuir.discussion.dto.requests.converters.MessageRequestConverter;
import by.bsuir.discussion.dto.responses.MessageResponseDto;
import by.bsuir.discussion.dto.responses.converters.CollectionMessageResponseConverter;
import by.bsuir.discussion.dto.responses.converters.MessageResponseConverter;
import by.bsuir.discussion.exceptions.EntityExistsException;
import by.bsuir.discussion.exceptions.Messages;
import by.bsuir.discussion.exceptions.NoEntityExistsException;
import by.bsuir.discussion.repositories.MessageRepository;
import by.bsuir.discussion.services.MessageService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageRequestConverter messageRequestConverter;
    private final MessageResponseConverter messageResponseConverter;
    private final CollectionMessageResponseConverter collectionMessageResponseConverter;
    @Override
    @Validated
    public MessageResponseDto create(@Valid @NonNull MessageRequestDto dto) throws EntityExistsException {
        Optional<Message> message = dto.getId() == null ? Optional.empty() : messageRepository.findMessageById(dto.getId());
        if (message.isEmpty()) {
            return messageResponseConverter.toDto(messageRepository.save(messageRequestConverter.fromDto(dto)));
        } else {
            throw new EntityExistsException(Messages.EntityExistsException);
        }
    }

    @Override
    public Optional<MessageResponseDto> read(@NonNull UUID id) {
        return messageRepository.findMessageById(id).flatMap(author -> Optional.of(
                messageResponseConverter.toDto(author)));
    }

    @Override
    @Validated
    public MessageResponseDto update(@Valid @NonNull MessageRequestDto dto) throws NoEntityExistsException {
        Optional<Message> message = dto.getId() == null || messageRepository.findMessageByStoryIdAndId(
                dto.getStoryId(), dto.getId()).isEmpty() ?
                Optional.empty() : Optional.of(messageRequestConverter.fromDto(dto));
        return messageResponseConverter.toDto(messageRepository.save(message.orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException))));
    }

    @Override
    public UUID delete(@NonNull UUID id) throws NoEntityExistsException {
        Optional<Message> message = messageRepository.findMessageById(id);
        messageRepository.deleteMessageByStoryIdAndId(message.map(Message::getStoryId).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)), message.map(Message::getId).
                orElseThrow(() -> new NoEntityExistsException(Messages.NoEntityExistsException)));
        return message.get().getId();
    }

    @Override
    public List<MessageResponseDto> readAll() {
        return collectionMessageResponseConverter.toListDto(messageRepository.findAll());
    }
}

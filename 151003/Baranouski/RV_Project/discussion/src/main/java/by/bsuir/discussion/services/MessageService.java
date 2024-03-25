package by.bsuir.discussion.services;


import by.bsuir.discussion.dto.requests.MessageRequestDto;
import by.bsuir.discussion.dto.responses.MessageResponseDto;

import java.util.List;

public interface MessageService extends BaseService<MessageRequestDto, MessageResponseDto> {
    List<MessageResponseDto> readAll();
}

package by.bsuir.discussion.dto.responses.converters;

import by.bsuir.discussion.domain.Message;
import by.bsuir.discussion.dto.responses.MessageResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageResponseConverter {
    MessageResponseDto toDto(Message message);
}

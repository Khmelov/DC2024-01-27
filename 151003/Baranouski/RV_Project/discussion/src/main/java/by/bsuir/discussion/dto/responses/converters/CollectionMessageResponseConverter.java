package by.bsuir.discussion.dto.responses.converters;

import by.bsuir.discussion.domain.Message;
import by.bsuir.discussion.dto.responses.MessageResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MessageResponseConverter.class)
public interface CollectionMessageResponseConverter {
    List<MessageResponseDto> toListDto(List<Message> messages);
}

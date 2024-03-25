package by.bsuir.discussion.dto.requests.converters;

import by.bsuir.discussion.domain.Message;
import by.bsuir.discussion.dto.requests.MessageRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageRequestConverter {
    Message fromDto(MessageRequestDto message);
}

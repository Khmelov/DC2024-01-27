package by.bsuir.resttask.mapper;

import by.bsuir.resttask.dto.request.MessageRequestTo;
import by.bsuir.resttask.dto.response.MessageResponseTo;
import by.bsuir.resttask.entity.Message;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toEntity(MessageRequestTo request);
    MessageResponseTo toResponseTo(Message entity);
    Message updateEntity(@MappingTarget Message entityToUpdate, MessageRequestTo updateRequest);
}

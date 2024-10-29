package by.bsuir.discussionservice.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import by.bsuir.discussionservice.dto.response.MessageResponseTo;
import by.bsuir.discussionservice.entity.Message;
import by.bsuir.discussionservice.entity.MessageState;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    @Mapping(target = "key.id", source = "id")
    @Mapping(target = "key.newsId", source = "newsId")
    @Mapping(target = "key.country", source = "country", defaultValue = "Unspecified")
    Message toEntity(MessageRequestTo request);

    @Mapping(target = "key.id", source = "id")
    @Mapping(target = "key.newsId", source = "newsId")
    @Mapping(target = "key.country", source = "country", defaultValue = "Unspecified")
    Message toEntity(MessageRequestTo request, @Context MessageState state);

    @Mapping(target = "id", source = "entity.key.id")
    @Mapping(target = "newsId", source = "entity.key.newsId")
    MessageResponseTo toResponseTo(Message entity);
    
    @Mapping(target = "key.id", ignore = true)
    @Mapping(target = "key.newsId", source = "newsId")
    @Mapping(target = "key.country", source = "country", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message updateEntity(@MappingTarget Message entityToUpdate, MessageRequestTo updateRequest);
    
}

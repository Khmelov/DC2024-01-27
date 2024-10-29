package by.bsuir.jpatask.mapper;

import by.bsuir.jpatask.dto.request.MessageRequestTo;
import by.bsuir.jpatask.dto.response.MessageResponseTo;
import by.bsuir.jpatask.entity.Message;
import by.bsuir.jpatask.entity.News;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    @Mapping(target = "news", expression = "java(newsFromRequest)")
    Message toEntity(MessageRequestTo request, @Context News newsFromRequest);

    @Mapping(target = "newsId", source = "news.id")
    MessageResponseTo toResponseTo(Message entity);
    
    @Mapping(target = "news", expression = "java(newsFromUpdateRequest)")
    Message updateEntity(@MappingTarget Message entityToUpdate, MessageRequestTo updateRequest,
                         @Context News newsFromUpdateRequest);

}

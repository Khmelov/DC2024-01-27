package by.bsuir.publisherservice.client.discussionservice.mapper;

import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import by.bsuir.publisherservice.dto.request.MessageRequestTo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscussionServiceMessageMapper {

    @Mapping(target = "state", expression = "java(by.bsuir.publisherservice.dto.MessageState.PENDING)")
    DiscussionServiceMessageRequestTo toDiscussionServiceRequestTo(MessageRequestTo request, String country);
    
}

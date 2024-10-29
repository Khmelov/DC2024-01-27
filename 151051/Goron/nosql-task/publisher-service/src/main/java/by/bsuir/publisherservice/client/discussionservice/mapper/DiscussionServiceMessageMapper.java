package by.bsuir.publisherservice.client.discussionservice.mapper;

import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import by.bsuir.publisherservice.dto.request.MessageRequestTo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionServiceMessageMapper {
    DiscussionServiceMessageRequestTo toDiscussionServiceRequestTo(MessageRequestTo request, String country);
}

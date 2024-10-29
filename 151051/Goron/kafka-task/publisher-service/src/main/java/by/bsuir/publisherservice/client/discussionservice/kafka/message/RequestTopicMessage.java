package by.bsuir.publisherservice.client.discussionservice.kafka.message;

import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import lombok.Builder;

@Builder
public record RequestTopicMessage(
        DiscussionServiceOperation operation,
        DiscussionServiceMessageRequestTo requestMessage,
        Integer pageNumber,
        Integer pageSize
) {

}

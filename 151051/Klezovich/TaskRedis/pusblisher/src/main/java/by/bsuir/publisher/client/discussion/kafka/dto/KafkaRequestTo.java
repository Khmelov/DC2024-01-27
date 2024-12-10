package by.bsuir.publisher.client.discussion.kafka.dto;

import by.bsuir.publisher.client.discussion.model.request.DiscussionRequestTo;
import lombok.Builder;

@Builder
public record KafkaRequestTo(
        OperationType operation,
        DiscussionRequestTo noteRequestTo,
        Integer pageNumber,
        Integer pageSize
) {
}

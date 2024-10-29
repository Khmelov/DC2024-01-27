package by.bsuir.discussionservice.kafka.message;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import lombok.Builder;

@Builder
public record RequestTopicMessage(
        Operation operation,
        MessageRequestTo requestMessage,
        Integer pageNumber,
        Integer pageSize
) {

}

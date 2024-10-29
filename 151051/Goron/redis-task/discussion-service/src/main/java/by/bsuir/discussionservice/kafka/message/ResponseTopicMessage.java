package by.bsuir.discussionservice.kafka.message;

import java.util.List;

import by.bsuir.discussionservice.dto.response.MessageResponseTo;
import lombok.Builder;

@Builder
public record ResponseTopicMessage(
        List<MessageResponseTo> response,
        String error
) {
    public boolean isSuccessful() {
        return error == null;
    }
}

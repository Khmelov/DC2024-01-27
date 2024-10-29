package by.bsuir.publisherservice.client.discussionservice.kafka.message;

import java.util.List;

import by.bsuir.publisherservice.dto.response.MessageResponseTo;

public record ResponseTopicMessage(
        List<MessageResponseTo> response,
        String error
) {
    public boolean isSuccessful() {
        return error == null;
    }
}

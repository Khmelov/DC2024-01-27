package by.bsuir.publisher.client.discussion.kafka.dto;

import by.bsuir.publisher.model.dto.response.NoteResponseTo;

import java.util.List;

public record KafkaResponseTo(
        List<NoteResponseTo> response,
        String error
) {
    public boolean isSuccessful() {
        return error == null;
    }
}

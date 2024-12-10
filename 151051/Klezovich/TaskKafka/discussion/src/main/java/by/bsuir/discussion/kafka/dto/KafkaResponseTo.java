package by.bsuir.discussion.kafka.dto;

import by.bsuir.discussion.model.dto.response.NoteResponseTo;
import lombok.Builder;

import java.util.List;

@Builder
public record KafkaResponseTo(
        List<NoteResponseTo> response,
        String error
) {
    public boolean isSuccessful() {
        return error == null;
    }
}

package by.bsuir.discussion.kafka.dto;

import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import lombok.Builder;

@Builder
public record KafkaRequestTo(
        OperationType operation,
        NoteRequestTo noteRequestTo,
        Integer pageNumber,
        Integer pageSize
) {
}

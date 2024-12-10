package by.bsuir.publisher.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoteRequestTo(
        Long id,
        Long tweetId,

        @NotNull
        @Size(min = 2, max = 2048, message = "Content must be between 2 and 2048 characters.")
        String content
) {
}

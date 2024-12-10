package by.bsuir.publisher.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TweetRequestTo(
        Long id,
        Long userId,

        @NotNull
        @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters.")
        String title,

        @NotNull
        @Size(min = 4, max = 2048, message = "Content must be between 4 and 2048 characters.")
        String content
) {
}

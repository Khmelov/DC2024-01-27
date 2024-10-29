package by.bsuir.discussionservice.dto.request;

import by.bsuir.discussionservice.entity.MessageState;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MessageRequestTo(
    Long id,
    Long newsId,
    
    @NotNull
    @Size(min = 2, max = 2048, message = "Content must be between 8 and 128 characters")
    String content,

    @NotNull
    String country,

    @NotNull
    MessageState state
) {

}

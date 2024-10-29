package by.bsuir.publisherservice.client.discussionservice.dto.request;

import by.bsuir.publisherservice.dto.MessageState;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record DiscussionServiceMessageRequestTo(
    Long id,
    Long newsId,
    
    @NotNull
    @Size(min = 2, max = 2048, message = "Content must be between 8 and 128 characters")
    String content,

    @NotNull
    String country,

    @NonNull
    MessageState state
) {

}

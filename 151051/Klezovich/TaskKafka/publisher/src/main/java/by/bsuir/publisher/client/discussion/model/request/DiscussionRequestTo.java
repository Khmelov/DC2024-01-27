package by.bsuir.publisher.client.discussion.model.request;

import by.bsuir.publisher.client.discussion.model.StateType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record DiscussionRequestTo(
        Long id,
        Long tweetId,

        @NotNull
        @Size(min = 2, max = 2048, message = "Content must be between 2 and 2048 characters.")
        String content,

        @NotNull
        String country,

        @NotNull
        StateType state
){
}

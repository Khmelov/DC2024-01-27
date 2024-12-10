package by.bsuir.publisher.model.dto.response;

import by.bsuir.publisher.client.discussion.model.StateType;

import java.io.Serializable;

public record NoteResponseTo(
        Long id,
        Long tweetId,
        String content,
        StateType state
) implements Serializable {
}

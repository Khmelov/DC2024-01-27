package by.bsuir.discussion.model.dto.response;

import by.bsuir.discussion.model.entity.StateType;

public record NoteResponseTo(
        Long id,
        Long tweetId,
        String content,
        StateType state
) {
}

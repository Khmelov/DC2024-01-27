package by.bsuir.discussionservice.dto.response;

import by.bsuir.discussionservice.entity.MessageState;

public record MessageResponseTo(
    Long id,
    Long newsId,
    String content,
    MessageState state
) {

}

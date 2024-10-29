package by.bsuir.publisherservice.dto.response;

import by.bsuir.publisherservice.dto.MessageState;

public record MessageResponseTo(
    Long id,
    Long newsId,
    String content,
    MessageState state
) {

}

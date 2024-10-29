package by.bsuir.publisherservice.dto.response;

import by.bsuir.publisherservice.dto.MessageState;

import java.io.Serializable;

public record MessageResponseTo(
    Long id,
    Long newsId,
    String content,
    MessageState state
) implements Serializable {

}

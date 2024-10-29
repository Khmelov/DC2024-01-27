package by.bsuir.discussionservice.controller;

import java.util.UUID;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import by.bsuir.discussionservice.dto.response.MessageResponseTo;

public class MessageControllerTest extends RestControllerTest<MessageRequestTo, MessageResponseTo> {
    private static final Long FK_NEWS_ID = 123L;

    @Override
    protected MessageRequestTo getRequestTo() {
        return new MessageRequestTo(createId(), FK_NEWS_ID,
                                    "Test content" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                    "Test country");
    }

    @Override
    protected MessageRequestTo getUpdateRequestTo(MessageRequestTo originalRequest, Long updateEntityId) {
        return new MessageRequestTo(updateEntityId,
                                    originalRequest.newsId(),
                                    "Test content" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                    "Test country");
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/messages";
    }

    private static Long createId() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits());
    }
}

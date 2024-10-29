package by.bsuir.discussionservice.controller;

import java.util.UUID;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import by.bsuir.discussionservice.dto.request.MessageRequestTo;
import by.bsuir.discussionservice.dto.response.MessageResponseTo;
import by.bsuir.discussionservice.entity.MessageState;
import io.restassured.response.Response;

public class MessageControllerTest extends RestControllerTest<MessageRequestTo, MessageResponseTo> {
    private static final Long FK_NEWS_ID = 123L;

    @Test
    public void saveWithForbiddenWord_MessageStateShouldBeEqualDeclined() {
        MessageRequestTo request = createMessageWithForbiddenContent();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);

        saveResponse.then()
                    .assertThat()
                    .body("state", is("DECLINED"));

        deleteEntity(responseEntityId);
    }

    @Test
    public void updateWithForbiddenWord_MessageStateShouldBeEqualDeclined() {
        MessageRequestTo saveRequest = getRequestTo();
        Response saveResponse = saveRequest(saveRequest);
        Long saveResponseEntityId = getResponseId(saveResponse);
        
        MessageRequestTo updateRequest = createMessageWithForbiddenContent(saveResponseEntityId);
        Response updateResponse = updateRequest(updateRequest);

        updateResponse.then()
                      .assertThat()
                      .body("state", is("DECLINED"));

        deleteEntity(saveResponseEntityId);
    }

    private MessageRequestTo createMessageWithForbiddenContent() {
        return new MessageRequestTo(createId(), FK_NEWS_ID,
                                    "Test forbidden-word-1 content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                    "Test country",
                                    MessageState.PENDING);
    }

    private MessageRequestTo createMessageWithForbiddenContent(Long id) {
        return new MessageRequestTo(id, FK_NEWS_ID,
                                    "Test forbidden-word-1 content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                    "Test country",
                                    MessageState.PENDING);
    }

    @Override
    protected MessageRequestTo getRequestTo() {
        return new MessageRequestTo(createId(), FK_NEWS_ID,
                                    "Test content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                    "Test country",
                                    MessageState.PENDING);
    }

    @Override
    protected MessageRequestTo getUpdateRequestTo(MessageRequestTo originalRequest, Long updateEntityId) {
        return new MessageRequestTo(updateEntityId,
                                    originalRequest.newsId(),
                                    "Test content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                    "Test country",
                                    MessageState.PENDING);
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

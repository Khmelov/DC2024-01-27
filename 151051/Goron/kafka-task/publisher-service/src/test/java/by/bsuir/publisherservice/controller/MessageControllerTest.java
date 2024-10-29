package by.bsuir.publisherservice.controller;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import by.bsuir.publisherservice.dto.request.AuthorRequestTo;
import by.bsuir.publisherservice.dto.request.MessageRequestTo;
import by.bsuir.publisherservice.dto.request.NewsRequestTo;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import io.restassured.response.Response;

public class MessageControllerTest extends RestControllerTest<MessageRequestTo, MessageResponseTo> {
    private static Long fkAuthorId;
    private static Long fkNewsId;
    private static boolean isForeignKeyEntitiesCreated = false;

    @BeforeEach
    public void createForeighnKeyEntities() {
        createForeignKeyEntitiesIfNotCreated();
    }

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
        return new MessageRequestTo(null, fkNewsId,
                                    "Test forbidden-word-1 content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    private MessageRequestTo createMessageWithForbiddenContent(Long id) {
        return new MessageRequestTo(id, fkNewsId,
                                    "Test forbidden-word-1 content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @AfterAll
    public static void deleteForeighnKeyEntities() {
        if (isForeignKeyEntitiesCreated) {
            deleteForeignKeyEntity(fkAuthorId, "/authors");
            deleteForeignKeyEntity(fkNewsId, "/news");
        }
    }

    @Override
    protected MessageRequestTo getRequestTo() {
        return new MessageRequestTo(null, fkNewsId,
                                    "content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected MessageRequestTo getUpdateRequestTo(MessageRequestTo originalRequest, Long updateEntityId) {
        return new MessageRequestTo(updateEntityId,
                                    originalRequest.newsId(),
                                    "content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/messages";
    }

    private void createForeignKeyEntitiesIfNotCreated() {
        if (!isForeignKeyEntitiesCreated) {
            AuthorRequestTo author = new AuthorRequestTo(null,
                                                         "login" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                                         "password" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                                         "firstame" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                                         "lastname" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
            Response authResponse = createForeignKeyEntity(author, "/authors");                                                     
            fkAuthorId = getResponseId(authResponse);
            
            NewsRequestTo news = new NewsRequestTo(null, fkAuthorId,
                                                   "Title" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                                   "Content" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
            Response newsResponse = createForeignKeyEntity(news, "/news");
            fkNewsId = getResponseId(newsResponse);

            isForeignKeyEntitiesCreated = true;
        }
    }

}

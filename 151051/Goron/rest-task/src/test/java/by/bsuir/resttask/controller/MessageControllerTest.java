package by.bsuir.resttask.controller;

import org.junit.jupiter.api.AfterAll;

import by.bsuir.resttask.dto.request.AuthorRequestTo;
import by.bsuir.resttask.dto.request.MessageRequestTo;
import by.bsuir.resttask.dto.request.NewsRequestTo;
import by.bsuir.resttask.dto.response.MessageResponseTo;
import io.restassured.response.Response;

public class MessageControllerTest extends RestControllerTest<MessageRequestTo, MessageResponseTo> {
    private static Long fkAuthorId;
    private static Long fkNewsId;
    private static boolean isForeignKeyEntitiesCreated = false;

    @AfterAll
    public static void deleteForeighnKeyEntities() {
        if (isForeignKeyEntitiesCreated) {
            deleteForeignKeyEntity(fkAuthorId, "/authors");
            deleteForeignKeyEntity(fkNewsId, "/news");
        }
    }

    @Override
    protected MessageRequestTo getRequestTo() {
        createForeignKeyEntitiesIfNotCreated();
        return new MessageRequestTo(null, fkNewsId,
                                    "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected MessageRequestTo getUpdateRequestTo(MessageRequestTo originalRequest, Long updateEntityId) {
        return new MessageRequestTo(updateEntityId,
                                    originalRequest.newsId(),
                                    "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/messages";
    }

    private void createForeignKeyEntitiesIfNotCreated() {
        if (!isForeignKeyEntitiesCreated) {
            AuthorRequestTo author = new AuthorRequestTo(null, "login", "password",
                                                         "firstame", "lastname");
            Response authResponse = createForeignKeyEntity(author, "/authors");                                                     
            fkAuthorId = getResponseId(authResponse);
            
            NewsRequestTo news = new NewsRequestTo(null, fkAuthorId, "Title", "Content");
            Response newsResponse = createForeignKeyEntity(news, "/news");
            fkNewsId = getResponseId(newsResponse);

            isForeignKeyEntitiesCreated = true;
        }
    }

}

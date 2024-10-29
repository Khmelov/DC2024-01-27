package by.bsuir.publisherservice.controller;

import org.junit.jupiter.api.AfterAll;

import by.bsuir.publisherservice.dto.request.AuthorRequestTo;
import by.bsuir.publisherservice.dto.request.NewsRequestTo;
import by.bsuir.publisherservice.dto.response.NewsResponseTo;
import io.restassured.response.Response;

public class NewsControllerTest extends RestControllerTest<NewsRequestTo, NewsResponseTo> {
    private static Long fkAuthorId;
    private static boolean isForeignKeyEntitiesCreated = false;

    @AfterAll
    public static void deleteForeighnKeyEntities() {
        if (isForeignKeyEntitiesCreated) {
            deleteForeignKeyEntity(fkAuthorId, "/authors");
        }
    }

    @Override
    protected NewsRequestTo getRequestTo() {
        createForeignKeyEntitiesIfNotCreated();
        return new NewsRequestTo(null, fkAuthorId,
                                 "title"   + RANDOM_NUMBER_GENERATOR.nextInt(),
                                 "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected NewsRequestTo getUpdateRequestTo(NewsRequestTo originalRequest, Long updateEntityId) {
        return new NewsRequestTo(updateEntityId,
                                 originalRequest.authorId(),
                                 "title"   + RANDOM_NUMBER_GENERATOR.nextInt(),
                                 "content" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/news";
    }

    private void createForeignKeyEntitiesIfNotCreated() {
        if (!isForeignKeyEntitiesCreated) {
            AuthorRequestTo author = new AuthorRequestTo(null,
                                                         "login" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                                         "password" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                                         "firstame" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                                         "lastname" + RANDOM_NUMBER_GENERATOR.nextInt());
            Response authResponse = createForeignKeyEntity(author, "/authors");                                                     
            fkAuthorId = getResponseId(authResponse);

            isForeignKeyEntitiesCreated = true;
        }
    }
}

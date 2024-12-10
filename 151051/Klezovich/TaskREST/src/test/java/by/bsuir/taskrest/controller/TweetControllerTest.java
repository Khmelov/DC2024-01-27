package by.bsuir.taskrest.controller;

import by.bsuir.taskrest.model.dto.request.TweetRequestTo;
import by.bsuir.taskrest.model.dto.request.UserRequestTo;
import by.bsuir.taskrest.model.dto.response.TweetResponseTo;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;

public class TweetControllerTest extends AbstractControllerTest<TweetRequestTo, TweetResponseTo> {

    private static boolean isFKCreated = false;
    private static Long fkUserId;

    @Override
    protected String getRequestsMappingPath() {
        return "/tweets";
    }

    @Override
    protected TweetRequestTo getRequestTo() {
        createFKIfNotCreated();
        return new TweetRequestTo(null, fkUserId,
                "title" + random.nextInt(),
                "content" + random.nextInt());
    }

    @Override
    protected TweetRequestTo getUpdatedRequestTo(TweetRequestTo requestTo, Long entityId) {
        return new TweetRequestTo(entityId,
                requestTo.userId(),
                "title" + random.nextInt(),
                "content" + random.nextInt());
    }

    private void createFKIfNotCreated() {
        if (!isFKCreated) {
            UserRequestTo user = new UserRequestTo(null, "login", "password",
                    "firstame", "lastname");
            Response userResponse = createEntityFK(user, "/users");
            fkUserId = getResponseId(userResponse);

            isFKCreated = true;
        }
    }

    @AfterAll
    public static void deleteFK() {
        if (isFKCreated) {
            deleteEntityFK(fkUserId, "/users");
        }
    }
}

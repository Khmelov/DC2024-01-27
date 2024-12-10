package by.bsuir.taskrest.controller;

import by.bsuir.taskrest.model.dto.request.TweetRequestTo;
import by.bsuir.taskrest.model.dto.request.UserRequestTo;
import by.bsuir.taskrest.model.dto.request.NoteRequestTo;
import by.bsuir.taskrest.model.dto.response.NoteResponseTo;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;

public class NoteControllerTest extends AbstractControllerTest<NoteRequestTo, NoteResponseTo> {

    private static boolean isFKCreated = false;
    private static Long fkUserId;
    private static Long fkTweetId;

    @Override
    protected String getRequestsMappingPath() {
        return "/notes";
    }

    @AfterAll
    public static void deleteForeignKeyEntities() {
        if (isFKCreated) {
            deleteEntityFK(fkUserId, "/users");
            deleteEntityFK(fkTweetId, "/tweets");
        }
    }

    @Override
    protected NoteRequestTo getRequestTo() {
        createFKIfNotCreated();
        return new NoteRequestTo(null, fkTweetId,
                "content" + random.nextInt());
    }

    @Override
    protected NoteRequestTo getUpdatedRequestTo(NoteRequestTo requestTo, Long entityId) {
        return new NoteRequestTo(entityId,
                requestTo.tweetId(),
                "content" + random.nextInt());
    }

    private void createFKIfNotCreated() {
        if (!isFKCreated) {
            UserRequestTo User = new UserRequestTo(null, "login", "password",
                    "firstame", "lastname");
            Response authResponse = createEntityFK(User, "/users");
            fkUserId = getResponseId(authResponse);

            TweetRequestTo tweets = new TweetRequestTo(null, fkUserId, "Title", "Content");
            Response tweetsResponse = createEntityFK(tweets, "/tweets");
            fkTweetId = getResponseId(tweetsResponse);

            isFKCreated = true;
        }
    }
}

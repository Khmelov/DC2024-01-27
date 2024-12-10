package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.TweetRequestTo;
import by.bsuir.publisher.model.dto.request.UserRequestTo;
import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
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
            UserRequestTo user = new UserRequestTo(null,
                    "login" + random.nextInt(),
                    "password" + random.nextInt(),
                    "firstame" + random.nextInt(),
                    "lastname" + random.nextInt());
            Response authResponse = createEntityFK(user, "/users");
            fkUserId = getResponseId(authResponse);

            TweetRequestTo tweets = new TweetRequestTo(null, fkUserId,
                    "Title" + random.nextInt(), "Content" + random.nextInt());
            Response tweetsResponse = createEntityFK(tweets, "/tweets");
            fkTweetId = getResponseId(tweetsResponse);

            isFKCreated = true;
        }
    }
}

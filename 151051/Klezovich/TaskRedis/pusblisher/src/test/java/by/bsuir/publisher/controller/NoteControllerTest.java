package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.TweetRequestTo;
import by.bsuir.publisher.model.dto.request.UserRequestTo;
import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                "content" + random.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected NoteRequestTo getUpdatedRequestTo(NoteRequestTo requestTo, Long entityId) {
        return new NoteRequestTo(entityId,
                requestTo.tweetId(),
                "content" + random.nextInt(Integer.MAX_VALUE));
    }

    @BeforeEach
    public void createForeignKeyEntities() {
        createFKIfNotCreated();
    }

    @Test
    public void saveWithForbiddenWord() {
        NoteRequestTo requestTo = createMessageWithForbiddenContent();
        Response response = saveRequest(requestTo);
        Long entityId = getResponseId(response);

        response.then()
                .assertThat()
                .body("state", is("DECLINED"));

        deleteEntity(entityId);
    }

    @Test
    public void updateWithForbiddenWord() {
        NoteRequestTo requestTo = getRequestTo();
        Response response = saveRequest(requestTo);
        Long entityId = getResponseId(response);

        NoteRequestTo updateRequest = createMessageWithForbiddenContent(entityId);
        Response updateResponse = updateRequest(updateRequest);

        updateResponse.then()
                .assertThat()
                .body("state", is("DECLINED"));

        deleteEntity(entityId);
    }

    private NoteRequestTo createMessageWithForbiddenContent() {
        return new NoteRequestTo(null, fkTweetId,
                "Test forbidden-word-1 content" + random.nextInt(Integer.MAX_VALUE));
    }

    private NoteRequestTo createMessageWithForbiddenContent(Long id) {
        return new NoteRequestTo(id, fkTweetId,
                "Test forbidden-word-1 content" + random.nextInt(Integer.MAX_VALUE));
    }

    private void createFKIfNotCreated() {
        if (!isFKCreated) {
            UserRequestTo user = new UserRequestTo(null,
                    "login" + random.nextInt(Integer.MAX_VALUE),
                    "password" + random.nextInt(Integer.MAX_VALUE),
                    "firstame" + random.nextInt(Integer.MAX_VALUE),
                    "lastname" + random.nextInt(Integer.MAX_VALUE));
            Response authResponse = createEntityFK(user, "/users");
            fkUserId = getResponseId(authResponse);

            TweetRequestTo tweets = new TweetRequestTo(null, fkUserId,
                    "Title" + random.nextInt(Integer.MAX_VALUE), "Content" + random.nextInt(Integer.MAX_VALUE));
            Response tweetsResponse = createEntityFK(tweets, "/tweets");
            fkTweetId = getResponseId(tweetsResponse);

            isFKCreated = true;
        }
    }
}

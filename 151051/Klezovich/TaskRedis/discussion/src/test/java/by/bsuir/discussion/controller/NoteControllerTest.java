package by.bsuir.discussion.controller;

import static org.hamcrest.Matchers.is;
import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import by.bsuir.discussion.model.dto.response.NoteResponseTo;
import by.bsuir.discussion.model.entity.StateType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class NoteControllerTest extends AbstractControllerTest<NoteRequestTo, NoteResponseTo> {

    private static final Long fkTweetId = 88L;

    @Override
    protected String getRequestsMappingPath() {
        return "/notes";
    }

    @Override
    protected NoteRequestTo getRequestTo() {
        return new NoteRequestTo(
                generateKey(),
                fkTweetId,
                "content" + random.nextInt(Integer.MAX_VALUE),
                "country" + random.nextInt(Integer.MAX_VALUE),
                StateType.PENDING);
    }

    @Override
    protected NoteRequestTo getUpdatedRequestTo(NoteRequestTo requestTo, Long entityId) {
        return new NoteRequestTo(
                entityId,
                requestTo.tweetId(),
                "new content" + random.nextInt(Integer.MAX_VALUE),
                "new country" + random.nextInt(Integer.MAX_VALUE),
                StateType.PENDING);
    }

    private NoteRequestTo createNoteWithForbiddenContent() {
        return new NoteRequestTo(generateKey(), fkTweetId,
                "forbidden-word-1 content" + random.nextInt(Integer.MAX_VALUE),
                "country" + random.nextInt(Integer.MAX_VALUE),
                StateType.PENDING);
    }

    private NoteRequestTo createNoteWithForbiddenContent(Long entityId) {
        return new NoteRequestTo(entityId, fkTweetId,
                "forbidden-word-1 content" + random.nextInt(Integer.MAX_VALUE),
                "country" + random.nextInt(Integer.MAX_VALUE),
                StateType.PENDING);
    }

    @Test
    public void saveWithForbiddenWord() {
        NoteRequestTo requestTo = createNoteWithForbiddenContent();
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

        NoteRequestTo updateRequestTo = createNoteWithForbiddenContent(entityId);
        Response updateResponse = updateRequest(updateRequestTo);

        updateResponse.then()
                .assertThat()
                .body("state", is("DECLINED"));

        deleteEntity(entityId);
    }

    private static Long generateKey() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits());
    }
}

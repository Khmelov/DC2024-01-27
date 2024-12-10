package by.bsuir.discussion.controller;

import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import by.bsuir.discussion.model.dto.response.NoteResponseTo;

import java.util.UUID;

public class NoteControllerTest extends AbstractControllerTest<NoteRequestTo, NoteResponseTo> {

    private static Long fkTweetId = 88L;

    @Override
    protected String getRequestsMappingPath() {
        return "/notes";
    }

    @Override
    protected NoteRequestTo getRequestTo() {
        return new NoteRequestTo(generateKey(), fkTweetId,
                "content" + random.nextInt(), "country" + random.nextInt());
    }

    @Override
    protected NoteRequestTo getUpdatedRequestTo(NoteRequestTo requestTo, Long entityId) {
        return new NoteRequestTo(entityId, requestTo.tweetId(),
                "new content" + random.nextInt(), "new country" + random.nextInt());
    }

    private static Long generateKey() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits());
    }
}

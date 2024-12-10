package by.bsuir.taskjpa.model.dto.response;

public record NoteResponseTo(
        Long id,
        Long tweetId,
        String content
) {
}

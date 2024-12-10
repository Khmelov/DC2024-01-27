package by.bsuir.discussion.model.dto.response;

public record ErrorResponseTo(
        String errorMessage,
        Integer errorCode
) {
}

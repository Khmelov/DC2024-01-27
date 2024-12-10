package by.bsuir.taskrest.model.dto.response;

public record ErrorResponseTo(
        String errorMessage,
        Integer errorCode
) {
}

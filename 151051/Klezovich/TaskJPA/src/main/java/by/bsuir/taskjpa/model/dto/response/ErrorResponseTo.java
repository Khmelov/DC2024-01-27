package by.bsuir.taskjpa.model.dto.response;

public record ErrorResponseTo(
        String errorMessage,
        Integer errorCode
) {
}

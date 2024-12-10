package by.bsuir.publisher.model.dto.response;

public record ErrorResponseTo(
        String errorMessage,
        Integer errorCode
) {
}

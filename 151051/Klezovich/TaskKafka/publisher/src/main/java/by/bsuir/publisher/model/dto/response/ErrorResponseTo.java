package by.bsuir.publisher.model.dto.response;

import java.io.Serializable;

public record ErrorResponseTo(
        String errorMessage,
        Integer errorCode
) implements Serializable {
}

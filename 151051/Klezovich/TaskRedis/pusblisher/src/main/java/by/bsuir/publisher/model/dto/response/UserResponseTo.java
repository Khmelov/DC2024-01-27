package by.bsuir.publisher.model.dto.response;

import java.io.Serializable;

public record UserResponseTo (
        Long id,
        String login,
        String firstname,
        String lastname
) implements Serializable {
}

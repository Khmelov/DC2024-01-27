package by.bsuir.publisher.model.dto.response;

public record UserResponseTo (
        Long id,
        String login,
        String firstname,
        String lastname
) {
}

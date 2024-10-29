package by.bsuir.resttask.dto.response;

public record AuthorResponseTo(
    Long id,
    String login,
    String firstname,
    String lastname 
) {}

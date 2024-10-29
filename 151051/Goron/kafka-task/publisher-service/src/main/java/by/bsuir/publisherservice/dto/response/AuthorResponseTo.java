package by.bsuir.publisherservice.dto.response;

public record AuthorResponseTo(
    Long id,
    String login,
    String firstname,
    String lastname 
) {}

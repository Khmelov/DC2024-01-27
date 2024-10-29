package by.bsuir.publisherservice.dto.response;

import java.io.Serializable;

public record AuthorResponseTo(
    Long id,
    String login,
    String firstname,
    String lastname 
) implements Serializable {}

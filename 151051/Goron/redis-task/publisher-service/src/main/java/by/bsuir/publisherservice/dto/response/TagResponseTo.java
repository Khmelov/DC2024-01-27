package by.bsuir.publisherservice.dto.response;

import java.io.Serializable;

public record TagResponseTo(
    Long id,
    String name
) implements Serializable {
    
}

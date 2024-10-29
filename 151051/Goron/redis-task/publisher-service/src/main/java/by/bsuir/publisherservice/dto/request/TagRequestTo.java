package by.bsuir.publisherservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TagRequestTo(
    Long id,
    
    @NotNull
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters")
    String name
) {
    
}

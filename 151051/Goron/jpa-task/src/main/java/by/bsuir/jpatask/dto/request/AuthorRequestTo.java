package by.bsuir.jpatask.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorRequestTo(
    Long id,
    
    @NotNull
    @Size(min = 2, max = 64, message = "Login must be between 2 and 64 characters")
    String login,
    
    @NotNull
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    String password,
    
    @NotNull
    @Size(min = 2, max = 64, message = "First name must be between 2 and 64 characters")
    String firstname,
        
    @NotNull
    @Size(min = 2, max = 64, message = "Last name must be between 2 and 64 characters")
    String lastname    
) {

}

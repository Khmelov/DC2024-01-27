package by.bsuir.taskrest.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StickerRequestTo(
    Long id,

    @NotNull
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters.")
    String name
) {
}

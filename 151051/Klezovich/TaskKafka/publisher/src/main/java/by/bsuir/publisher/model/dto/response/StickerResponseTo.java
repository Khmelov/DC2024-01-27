package by.bsuir.publisher.model.dto.response;

import java.io.Serializable;

public record StickerResponseTo(
        Long id,
        String name
) implements Serializable {
}

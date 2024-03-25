package by.bsuir.discussion.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@Data
public abstract class BaseRequestDto {
    private UUID id = UUID.randomUUID();
}

package by.bsuir.discussion.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@Data
public abstract class BaseResponseDto {
    private UUID id;
}

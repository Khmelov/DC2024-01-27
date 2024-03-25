package by.bsuir.publisher.dto.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class MessageRequestDto {
    private UUID id = UUID.randomUUID();
    private Long storyId;

    @Size(min = 3, max = 32)
    private String content;
}

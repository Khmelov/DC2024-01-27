package by.bsuir.publisher.dto.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class MessageResponseDto {
    private UUID id;
    private Long storyId;
    private String content;
}

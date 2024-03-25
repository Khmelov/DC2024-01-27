package by.bsuir.discussion.dto.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageRequestDto extends BaseRequestDto {
    private Long storyId;

    @Size(min = 3, max = 32)
    private String content;
}

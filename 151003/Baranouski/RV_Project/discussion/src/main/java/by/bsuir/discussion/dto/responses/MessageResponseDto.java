package by.bsuir.discussion.dto.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageResponseDto extends BaseResponseDto {
    private Long storyId;
    private String content;
}

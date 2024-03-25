package by.bsuir.publisher.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class ErrorDto {
    private String code;
    private String message;
}

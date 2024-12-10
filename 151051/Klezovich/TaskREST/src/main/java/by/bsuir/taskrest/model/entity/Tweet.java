package by.bsuir.taskrest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Tweet extends Entity {
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
}

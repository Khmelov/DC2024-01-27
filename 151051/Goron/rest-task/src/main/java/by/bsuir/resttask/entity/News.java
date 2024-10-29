package by.bsuir.resttask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class News extends Entity {

    private Long authorId;
    private String title;
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeModified;

}

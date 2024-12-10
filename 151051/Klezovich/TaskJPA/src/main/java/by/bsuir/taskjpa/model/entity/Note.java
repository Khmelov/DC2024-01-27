package by.bsuir.taskjpa.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tbl_note")
public class Note extends AbstractEntity {

    @ManyToOne()
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @Column(length = 2048, nullable = false)
    private String content;
}

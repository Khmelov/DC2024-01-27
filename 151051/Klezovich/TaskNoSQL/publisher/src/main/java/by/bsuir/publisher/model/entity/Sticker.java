package by.bsuir.publisher.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tbl_sticker")
public class Sticker extends AbstractEntity {

    @Column(length = 32, nullable = false)
    private String name;
}

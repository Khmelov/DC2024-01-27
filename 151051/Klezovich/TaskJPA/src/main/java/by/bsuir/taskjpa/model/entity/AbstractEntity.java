package by.bsuir.taskjpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;
}

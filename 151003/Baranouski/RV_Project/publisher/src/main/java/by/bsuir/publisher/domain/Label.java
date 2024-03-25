package by.bsuir.publisher.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "tbl_label")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Label extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "label")
    private List<StoryLabel> storyLabels;
}

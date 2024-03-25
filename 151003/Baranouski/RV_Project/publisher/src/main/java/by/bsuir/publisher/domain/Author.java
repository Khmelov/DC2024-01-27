package by.bsuir.publisher.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "tbl_author")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Author extends BaseEntity {
    @Column(unique = true)
    private String login;
    private String password;
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "author")
    private List<Story> stories;
}

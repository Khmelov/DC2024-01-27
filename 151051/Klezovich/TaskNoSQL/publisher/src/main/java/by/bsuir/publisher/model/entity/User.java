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
@Table(name = "tbl_user")
public class User extends AbstractEntity {

    @Column(length = 64, nullable = false, unique = true)
    private String login;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String firstname;

    @Column(length = 64, nullable = false)
    private String lastname;
}

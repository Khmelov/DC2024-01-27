package by.bsuir.jpatask.entity;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@jakarta.persistence.Entity
@Table(name = "tbl_tag")
public class Tag extends Entity {

    @Column(length = 32, nullable = false)
    private String name;
    
    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }

        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer()
                                                                                               .getPersistentClass() 
                                                                    : object.getClass();
                                                                    
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                                                                              .getPersistentClass() 
                                                                     : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) { 
            return false;
        }

        Tag tag = (Tag) object;

        return this.getId() != null && this.getId() == tag.getId();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                                                       .getPersistentClass().hashCode()
                                              : this.getClass().hashCode();
    }

}

package by.bsuir.jpatask.entity;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_message")
public class Message extends Entity {
    
    @ManyToOne()
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @Column(length = 2048, nullable = false)
    private String content;

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

        Message message = (Message) object;

        return this.getId() != null && this.getId() == message.getId();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                                                       .getPersistentClass().hashCode()
                                              : this.getClass().hashCode();
    }

}

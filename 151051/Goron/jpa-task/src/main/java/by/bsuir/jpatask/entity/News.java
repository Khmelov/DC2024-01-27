package by.bsuir.jpatask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@jakarta.persistence.Entity
@Table(name = "tbl_news")
public class News extends Entity {

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(length = 64, nullable = false, unique = true)
    private String title;
    
    @Column(length = 2048, nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timeCreated;
    
    @Column(nullable = false)
    private LocalDateTime timeModified;
    
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

        News news = (News) object;

        return this.getId() != null && this.getId() == news.getId();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                                                       .getPersistentClass().hashCode()
                                              : this.getClass().hashCode();
    }

}

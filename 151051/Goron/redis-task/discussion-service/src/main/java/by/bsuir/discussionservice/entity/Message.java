package by.bsuir.discussionservice.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("tbl_message")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Message {

    @PrimaryKeyClass
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Key implements Serializable  {
        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String country;
        
        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private Long id;
        
        @PrimaryKeyColumn(name = "news_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
        private Long newsId;
    }

    @PrimaryKey
    private Key key;

    @Column("content")
    private String content;

    @Column("state")
    private MessageState state;
}

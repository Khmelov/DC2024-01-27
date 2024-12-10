package by.bsuir.discussion.model.entity;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table("tbl_note")
public class Note {

    @PrimaryKeyClass
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Key implements Serializable {
        @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String country;

        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private Long id;

        @PrimaryKeyColumn(name = "tweet_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
        private Long tweetId;
    }

    @PrimaryKey
    private Key key;

    @Column("content")
    private String content;

    @Column("state")
    private StateType state;
}

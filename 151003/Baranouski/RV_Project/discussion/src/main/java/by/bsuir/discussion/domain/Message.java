package by.bsuir.discussion.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("tbl_message")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @PrimaryKeyColumn(
            name = "id",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.ASCENDING
    )
    private UUID id;

    @PrimaryKeyColumn(
            name = "story_id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED
    )
    private Long storyId;

    @Column("content")
    private String content;
}

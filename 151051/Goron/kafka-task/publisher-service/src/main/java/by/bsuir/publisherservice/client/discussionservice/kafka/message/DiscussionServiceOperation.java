package by.bsuir.publisherservice.client.discussionservice.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum DiscussionServiceOperation {
    GET_BY_ID("get_by_id"),
    GET_ALL("get_all"),
    SAVE("save"),
    UPDATE("update"),
    DELETE("delete");    

    private final String OPERATION;
}

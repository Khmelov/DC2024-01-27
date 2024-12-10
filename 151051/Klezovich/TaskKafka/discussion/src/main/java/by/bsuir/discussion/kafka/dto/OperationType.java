package by.bsuir.discussion.kafka.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OperationType {
    SAVE("save"),
    FIND_ALL("find_all"),
    FIND_BY_ID("find_by_id"),
    UPDATE("update"),
    DELETE("delete");

    private String operation;

    OperationType(String operation) {
        this.operation = operation;
    }
}

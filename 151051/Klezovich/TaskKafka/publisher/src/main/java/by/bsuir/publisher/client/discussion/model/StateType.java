package by.bsuir.publisher.client.discussion.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StateType {
    PENDING("pending"),
    APPROVED("approved"),
    DECLINED("declined");

    private final String state;

    StateType(String state) {
        this.state = state;
    }
}

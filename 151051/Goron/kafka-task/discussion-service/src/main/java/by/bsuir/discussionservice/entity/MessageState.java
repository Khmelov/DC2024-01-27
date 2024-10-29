package by.bsuir.discussionservice.entity;

import lombok.Getter;

@Getter
public enum MessageState {
    PENDING("pending"),
    APPROVED("approved"),
    DECLINED("declined");

    private String state;

    MessageState(String state) {
        this.state = state;
    }
}

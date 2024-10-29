package by.bsuir.publisherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum MessageState {
    PENDING("Pending"),
    APPROVED("Approved"),
    DECLINED("Declined");

    private final String STATE;
}

package by.bsuir.discussionservice.service;

import java.util.List;

import org.springframework.stereotype.Component;

import by.bsuir.discussionservice.entity.Message;
import by.bsuir.discussionservice.entity.MessageState;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class MessageModerator {
    
    private List<String> forbiddenWords;

    public MessageModerator() {
        forbiddenWords = Arrays.asList(
            "forbidden-word-1",
            "forbidden-word-2",
            "forbidden-word-3"
        );
    }

    public Message moderateMessage(Message message) {
        for (var forbiddenWord : forbiddenWords) {
            if (message.getContent().matches(".*\\b" + forbiddenWord + "\\b.*")) {
                message.setState(MessageState.DECLINED);
                return message;
            }
        }

        message.setState(MessageState.APPROVED);
        return message;
    }

}

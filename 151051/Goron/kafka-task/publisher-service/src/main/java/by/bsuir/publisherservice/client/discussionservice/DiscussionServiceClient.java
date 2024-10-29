package by.bsuir.publisherservice.client.discussionservice;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import jakarta.validation.Valid;

public interface DiscussionServiceClient {
    public MessageResponseTo getMessageById(@PathVariable Long id);
    public List<MessageResponseTo> getAllMessages(
        @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
        @RequestParam(name = "size", defaultValue = "5") Integer pageSize);
    public MessageResponseTo saveMessage(@RequestBody @Valid DiscussionServiceMessageRequestTo message);
    public MessageResponseTo updateMessage(@RequestBody @Valid DiscussionServiceMessageRequestTo message);
    public void deleteMessage(@PathVariable Long id);
}

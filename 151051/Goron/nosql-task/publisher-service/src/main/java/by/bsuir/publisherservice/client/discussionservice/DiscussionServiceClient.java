package by.bsuir.publisherservice.client.discussionservice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import by.bsuir.publisherservice.client.discussionservice.dto.request.DiscussionServiceMessageRequestTo;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import jakarta.validation.Valid;

@HttpExchange("/messages")
public interface DiscussionServiceClient {

    @GetExchange("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseTo getMessageById(@PathVariable Long id);

    @GetExchange
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseTo> getAllMessages(@RequestParam(name = "page", defaultValue = "0")
                                                  Integer pageNumber,
                                                  @RequestParam(name = "size", defaultValue = "5")
                                                  Integer pageSize);

    @PostExchange
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseTo saveMessage(@RequestBody @Valid DiscussionServiceMessageRequestTo message);

    @PutExchange
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseTo updateMessage(@RequestBody @Valid DiscussionServiceMessageRequestTo message);
    
    @DeleteExchange("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long id);

}

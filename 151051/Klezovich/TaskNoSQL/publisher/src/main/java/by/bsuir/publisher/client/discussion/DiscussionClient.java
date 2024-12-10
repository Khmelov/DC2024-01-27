package by.bsuir.publisher.client.discussion;

import by.bsuir.publisher.client.discussion.model.request.DiscussionRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/notes")
public interface DiscussionClient {

    @PostExchange
    @ResponseStatus(HttpStatus.CREATED)
    NoteResponseTo save(@RequestBody @Valid DiscussionRequestTo entity);

    @GetExchange
    @ResponseStatus(HttpStatus.OK)
    List<NoteResponseTo> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                 @RequestParam(defaultValue = "5") Integer pageSize);

    @GetExchange("/{id}")
    @ResponseStatus(HttpStatus.OK)
    NoteResponseTo findById(@PathVariable Long id);

    @PutExchange
    @ResponseStatus(HttpStatus.OK)
    NoteResponseTo update(@RequestBody @Valid DiscussionRequestTo entity);

    @DeleteExchange("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @Valid Long id);
}

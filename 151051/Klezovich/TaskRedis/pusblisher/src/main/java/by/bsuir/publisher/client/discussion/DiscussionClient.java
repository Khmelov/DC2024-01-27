package by.bsuir.publisher.client.discussion;

import by.bsuir.publisher.client.discussion.model.request.DiscussionRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DiscussionClient {
    NoteResponseTo save(@RequestBody @Valid DiscussionRequestTo entity);
    List<NoteResponseTo> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                 @RequestParam(defaultValue = "5") Integer pageSize);
    NoteResponseTo findById(@PathVariable Long id);
    NoteResponseTo update(@RequestBody @Valid DiscussionRequestTo entity);
    void delete(@PathVariable @Valid Long id);
}

package by.bsuir.jpatask.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import by.bsuir.jpatask.dto.request.MessageRequestTo;
import by.bsuir.jpatask.dto.response.MessageResponseTo;
import by.bsuir.jpatask.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController extends by.bsuir.jpatask.controller.RestController {

    private final MessageService MESSAGE_SERVICE;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseTo getById(@PathVariable Long id) {
        return MESSAGE_SERVICE.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseTo> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "5") Integer pageSize,
                                          @RequestParam(defaultValue = "id,desc") String[] sortParameters) {
        
        List<Order> sortOrders = getSortOrders(sortParameters);
        Pageable restriction = PageRequest.of(pageNumber, pageSize, Sort.by(sortOrders));

        return MESSAGE_SERVICE.getAll(restriction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseTo save(@RequestBody @Valid MessageRequestTo message) {
        return MESSAGE_SERVICE.save(message);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseTo update(@RequestBody @Valid MessageRequestTo message) {
        return MESSAGE_SERVICE.update(message);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MESSAGE_SERVICE.delete(id);
    }
}

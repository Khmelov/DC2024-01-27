package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.TweetRequestTo;
import by.bsuir.publisher.model.dto.response.TweetResponseTo;
import by.bsuir.publisher.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController extends AbstractController {

    private final TweetService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseTo save(@RequestBody @Valid TweetRequestTo entity) {
        return service.save(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TweetResponseTo> findAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                         @RequestParam(name = "size", defaultValue = "5") Integer pageSize,
                                         @RequestParam(name = "sort", defaultValue = "id,desc") String[] sortParameters) {

        List<Order> sortOrders = getSortOrderList(sortParameters);
        Pageable restriction = PageRequest.of(pageNumber, pageSize, Sort.by(sortOrders));
        return service.findAll(restriction);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TweetResponseTo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TweetResponseTo update(@RequestBody @Valid TweetRequestTo entity) {
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        service.delete(id);
    }
}

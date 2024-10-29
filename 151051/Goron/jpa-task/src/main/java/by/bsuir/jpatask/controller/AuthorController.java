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

import by.bsuir.jpatask.dto.request.AuthorRequestTo;
import by.bsuir.jpatask.dto.response.AuthorResponseTo;
import by.bsuir.jpatask.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController extends by.bsuir.jpatask.controller.RestController {

    private final AuthorService AUTHOR_SERVICE;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponseTo getById(@PathVariable Long id) {
        return AUTHOR_SERVICE.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponseTo> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                         @RequestParam(defaultValue = "5") Integer pageSize,
                                         @RequestParam(defaultValue = "id,desc") String[] sortParameters) {
        
        List<Order> sortOrders = getSortOrders(sortParameters);
        Pageable restriction = PageRequest.of(pageNumber, pageSize, Sort.by(sortOrders));

        return AUTHOR_SERVICE.getAll(restriction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponseTo save(@RequestBody @Valid AuthorRequestTo author) {
        return AUTHOR_SERVICE.save(author);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponseTo update(@RequestBody @Valid AuthorRequestTo author) {
        return AUTHOR_SERVICE.update(author);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        AUTHOR_SERVICE.delete(id);
    }
}

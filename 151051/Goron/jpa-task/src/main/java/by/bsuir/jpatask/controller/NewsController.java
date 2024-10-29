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

import by.bsuir.jpatask.dto.request.NewsRequestTo;
import by.bsuir.jpatask.dto.response.NewsResponseTo;
import by.bsuir.jpatask.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController extends by.bsuir.jpatask.controller.RestController {

    private final NewsService NEWS_SERVICE;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsResponseTo getById(@PathVariable Long id) {
        return NEWS_SERVICE.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NewsResponseTo> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(defaultValue = "id,desc") String[] sortParameters) {
        
        List<Order> sortOrders = getSortOrders(sortParameters);
        Pageable restriction = PageRequest.of(pageNumber, pageSize, Sort.by(sortOrders));

        return NEWS_SERVICE.getAll(restriction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponseTo save(@RequestBody @Valid NewsRequestTo news) {
        return NEWS_SERVICE.save(news);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public NewsResponseTo update(@RequestBody @Valid NewsRequestTo news) {
        return NEWS_SERVICE.update(news);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        NEWS_SERVICE.delete(id);
    }
}

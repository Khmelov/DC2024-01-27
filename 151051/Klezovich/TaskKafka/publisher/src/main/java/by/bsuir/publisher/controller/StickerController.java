package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.StickerRequestTo;
import by.bsuir.publisher.model.dto.response.StickerResponseTo;
import by.bsuir.publisher.service.StickerService;
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
@RequestMapping("/stickers")
public class StickerController extends AbstractController {

    private final StickerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StickerResponseTo save(@RequestBody @Valid StickerRequestTo entity) {
        return service.save(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StickerResponseTo> findAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                           @RequestParam(name = "size", defaultValue = "5") Integer pageSize,
                                           @RequestParam(name = "sort", defaultValue = "id,desc") String[] sortParameters) {

        List<Order> sortOrders = getSortOrderList(sortParameters);
        Pageable restriction = PageRequest.of(pageNumber, pageSize, Sort.by(sortOrders));
        return service.findAll(restriction);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StickerResponseTo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public StickerResponseTo update(@RequestBody @Valid StickerRequestTo entity) {
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        service.delete(id);
    }
}

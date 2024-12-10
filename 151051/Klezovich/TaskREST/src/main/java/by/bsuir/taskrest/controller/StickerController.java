package by.bsuir.taskrest.controller;

import by.bsuir.taskrest.model.dto.request.StickerRequestTo;
import by.bsuir.taskrest.model.dto.response.StickerResponseTo;
import by.bsuir.taskrest.service.StickerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers")
public class StickerController {

    private final StickerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StickerResponseTo save(@RequestBody @Valid StickerRequestTo entity) {
        return service.save(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StickerResponseTo> findAll() {
        return service.findAll();
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

package by.bsuir.taskrest.controller;

import by.bsuir.taskrest.model.dto.request.NoteRequestTo;
import by.bsuir.taskrest.model.dto.response.NoteResponseTo;
import by.bsuir.taskrest.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponseTo save(@RequestBody @Valid NoteRequestTo entity) {
        return service.save(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NoteResponseTo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoteResponseTo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public NoteResponseTo update(@RequestBody @Valid NoteRequestTo entity) {
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        service.delete(id);
    }
}

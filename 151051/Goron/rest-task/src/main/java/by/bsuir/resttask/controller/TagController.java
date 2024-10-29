package by.bsuir.resttask.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import by.bsuir.resttask.dto.request.TagRequestTo;
import by.bsuir.resttask.dto.response.TagResponseTo;
import by.bsuir.resttask.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService TAG_SERVICE;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponseTo getById(@PathVariable Long id) {
        return TAG_SERVICE.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponseTo> getAll() {
        return TAG_SERVICE.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponseTo save(@RequestBody @Valid TagRequestTo tag) {
        return TAG_SERVICE.save(tag);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TagResponseTo update(@RequestBody @Valid TagRequestTo tag) {
        return TAG_SERVICE.update(tag);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        TAG_SERVICE.delete(id);
    }
}

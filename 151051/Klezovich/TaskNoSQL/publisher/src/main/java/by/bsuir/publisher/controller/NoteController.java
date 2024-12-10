package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import by.bsuir.publisher.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController extends AbstractController {

    private final NoteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponseTo save(@RequestBody @Valid NoteRequestTo entity, HttpServletRequest request) {
        return service.save(entity, getCountryFromRequest(request));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NoteResponseTo> findAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                        @RequestParam(name = "size", defaultValue = "5") Integer pageSize) {

        Pageable restriction = PageRequest.of(pageNumber, pageSize);
        return service.findAll(restriction);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoteResponseTo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public NoteResponseTo update(@RequestBody @Valid NoteRequestTo entity, HttpServletRequest request) {
        return service.update(entity, getCountryFromRequest(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        service.delete(id);
    }

    private static String getCountryFromRequest(HttpServletRequest request) {
        String requestCountry = request.getLocale().getDisplayCountry();
        return requestCountry.isEmpty() ? "Not found" : requestCountry;
    }
}

package by.bsuir.publisher.controllers;

import by.bsuir.publisher.dto.requests.AuthorRequestDto;
import by.bsuir.publisher.dto.responses.AuthorResponseDto;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.Messages;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import by.bsuir.publisher.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@RequestBody AuthorRequestDto author) throws EntityExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> read(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.read(id).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.readAll());
    }

    @PutMapping
    public ResponseEntity<AuthorResponseDto> update(@RequestBody AuthorRequestDto author) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.update(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(authorService.delete(id));
    }
}

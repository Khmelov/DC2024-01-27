package by.bsuir.publisher.controllers;

import by.bsuir.publisher.dto.requests.LabelRequestDto;
import by.bsuir.publisher.dto.responses.LabelResponseDto;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.Messages;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import by.bsuir.publisher.services.LabelService;
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
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @PostMapping
    public ResponseEntity<LabelResponseDto> create(@RequestBody LabelRequestDto label) throws EntityExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(labelService.create(label));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelResponseDto> read(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(labelService.read(id).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
    }

    @GetMapping
    public ResponseEntity<List<LabelResponseDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(labelService.readAll());
    }

    @PutMapping
    public ResponseEntity<LabelResponseDto> update(@RequestBody LabelRequestDto label) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(labelService.update(label));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(labelService.delete(id));
    }
}

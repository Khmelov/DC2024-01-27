package by.bsuir.publisher.controllers;

import by.bsuir.publisher.dto.requests.StoryRequestDto;
import by.bsuir.publisher.dto.responses.StoryResponseDto;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.Messages;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import by.bsuir.publisher.services.StoryService;
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
@RequestMapping("/storys")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @PostMapping
    public ResponseEntity<StoryResponseDto> create(@RequestBody StoryRequestDto story) throws EntityExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(storyService.create(story));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryResponseDto> read(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(storyService.read(id).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
    }

    @GetMapping
    public ResponseEntity<List<StoryResponseDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(storyService.readAll());
    }

    @PutMapping
    public ResponseEntity<StoryResponseDto> update(@RequestBody StoryRequestDto story) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(storyService.update(story));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(storyService.delete(id));
    }
}

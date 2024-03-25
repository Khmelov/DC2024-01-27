package by.bsuir.discussion.controllers;

import by.bsuir.discussion.dto.requests.MessageRequestDto;
import by.bsuir.discussion.dto.responses.MessageResponseDto;
import by.bsuir.discussion.exceptions.EntityExistsException;
import by.bsuir.discussion.exceptions.Messages;
import by.bsuir.discussion.exceptions.NoEntityExistsException;
import by.bsuir.discussion.services.MessageService;
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
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> create(@RequestBody MessageRequestDto message) throws EntityExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDto> read(@PathVariable("id") UUID id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.read(id).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.readAll());
    }

    @PutMapping
    public ResponseEntity<MessageResponseDto> update(@RequestBody MessageRequestDto message) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.update(message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messageService.delete(id));
    }
}

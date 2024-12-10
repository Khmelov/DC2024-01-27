package by.bsuir.discussion.kafka.service;

import by.bsuir.discussion.exception.EntityNotFoundException;
import by.bsuir.discussion.exception.EntitySavingException;
import by.bsuir.discussion.kafka.dto.KafkaRequestTo;
import by.bsuir.discussion.kafka.dto.KafkaResponseTo;
import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import by.bsuir.discussion.model.dto.response.NoteResponseTo;
import by.bsuir.discussion.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaHandlerService {
    private final NoteService service;

    public KafkaResponseTo handleMessage(KafkaRequestTo kafkaRequestTo) {
        return switch (kafkaRequestTo.operation()) {
            case SAVE -> save(kafkaRequestTo.noteRequestTo());
            case FIND_ALL -> findAll(kafkaRequestTo.pageNumber(), kafkaRequestTo.pageSize());
            case FIND_BY_ID -> findById(kafkaRequestTo.noteRequestTo().id());
            case UPDATE -> update(kafkaRequestTo.noteRequestTo());
            case DELETE -> delete(kafkaRequestTo.noteRequestTo().id());
        };
    }

    private KafkaResponseTo save(NoteRequestTo noteRequestTo) {
        try {
            NoteResponseTo note = service.save(noteRequestTo);
            return KafkaResponseTo.builder()
                    .response(List.of(note))
                    .build();
        }
        catch (EntitySavingException e) {
            return KafkaResponseTo.builder()
                    .error(e.getMessage())
                    .build();
        }
    }

    private KafkaResponseTo findAll(Integer pageNumber, Integer pageSize) {
        List<NoteResponseTo> noteList = service.findAll(PageRequest.of(pageNumber, pageSize));
        return KafkaResponseTo.builder()
                .response(noteList)
                .build();
    }

    private KafkaResponseTo findById(Long noteId) {
        try {
            NoteResponseTo note = service.findById(noteId);
            return KafkaResponseTo.builder()
                    .response(List.of(note))
                    .build();
        }
        catch (EntityNotFoundException e) {
            return KafkaResponseTo.builder()
                    .error(e.getMessage())
                    .build();
        }
    }

    private KafkaResponseTo update(NoteRequestTo noteRequestTo) {
        try {
            NoteResponseTo updatedNote = service.update(noteRequestTo);
            return KafkaResponseTo.builder()
                    .response(List.of(updatedNote))
                    .build();
        }
        catch (EntityNotFoundException| EntitySavingException e) {
            return KafkaResponseTo.builder()
                    .error(e.getMessage())
                    .build();
        }
    }

    private KafkaResponseTo delete(Long noteId) {
        try {
            service.delete(noteId);
            return KafkaResponseTo.builder().build();
        }
        catch (EntityNotFoundException| EntitySavingException e) {
            return KafkaResponseTo.builder()
                    .error(e.getMessage())
                    .build();
        }
    }
}

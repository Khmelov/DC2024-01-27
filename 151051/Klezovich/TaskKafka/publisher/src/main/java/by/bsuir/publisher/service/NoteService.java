package by.bsuir.publisher.service;

import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    NoteResponseTo save(NoteRequestTo entity, String country);
    List<NoteResponseTo> findAll(Pageable restriction);
    NoteResponseTo findById(Long id);
    NoteResponseTo update(NoteRequestTo updateRequest, String country);
    void delete(Long id);
}

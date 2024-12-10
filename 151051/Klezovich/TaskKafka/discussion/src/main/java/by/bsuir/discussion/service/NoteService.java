package by.bsuir.discussion.service;

import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import by.bsuir.discussion.model.dto.response.NoteResponseTo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    NoteResponseTo save(NoteRequestTo entity);
    List<NoteResponseTo> findAll(Pageable restriction);
    NoteResponseTo findById(Long id);
    NoteResponseTo update(NoteRequestTo updateRequest);
    void delete(Long id);
}

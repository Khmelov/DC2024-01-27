package by.bsuir.taskrest.service;

import by.bsuir.taskrest.model.dto.request.NoteRequestTo;
import by.bsuir.taskrest.model.dto.response.NoteResponseTo;

import java.util.List;

public interface NoteService {
    NoteResponseTo save(NoteRequestTo entity);
    List<NoteResponseTo> findAll();
    NoteResponseTo findById(Long id);
    NoteResponseTo update(NoteRequestTo updateRequest);
    void delete(Long id);
}

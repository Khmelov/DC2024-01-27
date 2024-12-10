package by.bsuir.taskrest.repository.impl;

import by.bsuir.taskrest.model.entity.Note;
import by.bsuir.taskrest.repository.common.InMemoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("in-memory-repositories")
public class NoteRepository extends InMemoryRepository<Note> {
}

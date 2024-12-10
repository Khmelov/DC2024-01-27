package by.bsuir.discussion.repository;

import by.bsuir.discussion.model.entity.Note;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends CassandraRepository<Note, Note.Key> {

//    @AllowFiltering
    @Query(allowFiltering = true)
    Optional<Note> findByKeyId(Long id);
}

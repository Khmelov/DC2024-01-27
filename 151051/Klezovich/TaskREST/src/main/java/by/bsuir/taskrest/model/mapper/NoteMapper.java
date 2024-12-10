package by.bsuir.taskrest.model.mapper;

import by.bsuir.taskrest.model.dto.request.NoteRequestTo;
import by.bsuir.taskrest.model.dto.response.NoteResponseTo;
import by.bsuir.taskrest.model.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteMapper {
    Note toEntity(NoteRequestTo request);
    NoteResponseTo toResponseTo(Note entity);
    List<NoteResponseTo> toResponseTo(List<Note> entities);
}

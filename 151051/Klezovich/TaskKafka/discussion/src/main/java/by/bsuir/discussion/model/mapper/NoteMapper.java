package by.bsuir.discussion.model.mapper;

import by.bsuir.discussion.model.dto.request.NoteRequestTo;
import by.bsuir.discussion.model.dto.response.NoteResponseTo;
import by.bsuir.discussion.model.entity.Note;
import by.bsuir.discussion.model.entity.StateType;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteMapper {

    @Mapping(target = "key.id", source = "id")
    @Mapping(target = "key.tweetId", source = "tweetId")
    @Mapping(target = "key.country", source = "country", defaultValue = "Not found")
    Note toEntity(NoteRequestTo request);

    @Mapping(target = "key.id", source = "id")
    @Mapping(target = "key.tweetId", source = "tweetId")
    @Mapping(target = "key.country", source = "country", defaultValue = "Not found")
    Note toEntity(NoteRequestTo request, @Context StateType state);

    @Mapping(target = "key.id", ignore = true)
    @Mapping(target = "key.tweetId", source = "tweetId")
    @Mapping(target = "key.country", source = "country", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Note updateEntity(@MappingTarget Note entityToUpdate, NoteRequestTo updateRequest);

    @Mapping(target = "id", source = "entity.key.id")
    @Mapping(target = "tweetId", source = "entity.key.tweetId")
    NoteResponseTo toResponseTo(Note entity);
}

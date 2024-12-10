package by.bsuir.taskjpa.model.mapper;

import by.bsuir.taskjpa.model.dto.request.NoteRequestTo;
import by.bsuir.taskjpa.model.dto.response.NoteResponseTo;
import by.bsuir.taskjpa.model.entity.Note;
import by.bsuir.taskjpa.model.entity.Tweet;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteMapper {

    @Mapping(target = "tweet", expression = "java(tweetFromRequest)")
    Note toEntity(NoteRequestTo request, @Context Tweet tweetFromRequest);

    @Mapping(target = "tweet", expression = "java(tweetFromUpdateRequest)")
    Note updateEntity(@MappingTarget Note entityToUpdate, NoteRequestTo updateRequest, @Context Tweet tweetFromUpdateRequest);

    @Mapping(target = "tweetId", source = "tweet.id")
    NoteResponseTo toResponseTo(Note entity);
}

package by.bsuir.publisher.client.discussion.model.mapper;

import by.bsuir.publisher.client.discussion.model.request.DiscussionRequestTo;
import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DiscussionMapper {
    DiscussionRequestTo toRequestTo(NoteRequestTo noteRequestTo, String country);
}

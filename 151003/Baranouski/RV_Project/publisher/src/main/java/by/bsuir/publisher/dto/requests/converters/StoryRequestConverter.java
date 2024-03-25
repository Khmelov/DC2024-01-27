package by.bsuir.publisher.dto.requests.converters;

import by.bsuir.publisher.domain.Story;
import by.bsuir.publisher.dto.requests.StoryRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoryRequestConverter {
    @Mapping(source = "authorId", target = "author.id")
    Story fromDto(StoryRequestDto story);
}

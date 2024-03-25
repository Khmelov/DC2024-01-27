package by.bsuir.publisher.dto.responses.converters;

import by.bsuir.publisher.domain.Story;
import by.bsuir.publisher.dto.responses.StoryResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = StoryResponseConverter.class)
public interface CollectionStoryResponseConverter {
    List<StoryResponseDto> toListDto(List<Story> labels);
}

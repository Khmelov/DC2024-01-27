package by.bsuir.publisher.dto.responses.converters;

import by.bsuir.publisher.domain.Label;
import by.bsuir.publisher.dto.responses.LabelResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = LabelResponseConverter.class)
public interface CollectionLabelResponseConverter {
    List<LabelResponseDto> toListDto(List<Label> labels);
}

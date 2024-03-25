package by.bsuir.publisher.dto.responses.converters;

import by.bsuir.publisher.domain.Label;
import by.bsuir.publisher.dto.responses.LabelResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelResponseConverter {
    LabelResponseDto toDto(Label label);
}

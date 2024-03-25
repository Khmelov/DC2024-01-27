package by.bsuir.publisher.dto.requests.converters;

import by.bsuir.publisher.domain.Label;
import by.bsuir.publisher.dto.requests.LabelRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelRequestConverter {
    Label fromDto(LabelRequestDto label);
}

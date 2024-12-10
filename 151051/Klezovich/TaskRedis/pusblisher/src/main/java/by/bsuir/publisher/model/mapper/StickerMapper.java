package by.bsuir.publisher.model.mapper;

import by.bsuir.publisher.model.dto.request.StickerRequestTo;
import by.bsuir.publisher.model.dto.response.StickerResponseTo;
import by.bsuir.publisher.model.entity.Sticker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StickerMapper {

    @Mapping(target = "id", ignore = true)
    Sticker toEntity(StickerRequestTo request);

    @Mapping(target = "id", ignore = true)
    Sticker updateEntity(@MappingTarget Sticker entityToUpdate, StickerRequestTo updateRequest);

    StickerResponseTo toResponseTo(Sticker entity);
}

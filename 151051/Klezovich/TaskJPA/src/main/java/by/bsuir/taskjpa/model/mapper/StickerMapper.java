package by.bsuir.taskjpa.model.mapper;

import by.bsuir.taskjpa.model.dto.request.StickerRequestTo;
import by.bsuir.taskjpa.model.dto.response.StickerResponseTo;
import by.bsuir.taskjpa.model.entity.Sticker;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StickerMapper {
    Sticker toEntity(StickerRequestTo request);
    Sticker updateEntity(@MappingTarget Sticker entityToUpdate, StickerRequestTo updateRequest);
    StickerResponseTo toResponseTo(Sticker entity);
}

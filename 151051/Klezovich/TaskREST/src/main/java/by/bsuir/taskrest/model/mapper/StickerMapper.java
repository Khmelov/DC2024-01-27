package by.bsuir.taskrest.model.mapper;

import by.bsuir.taskrest.model.dto.request.StickerRequestTo;
import by.bsuir.taskrest.model.dto.response.StickerResponseTo;
import by.bsuir.taskrest.model.entity.Sticker;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StickerMapper {
    Sticker toEntity(StickerRequestTo request);
    StickerResponseTo toResponseTo(Sticker entity);
    List<StickerResponseTo> toResponseTo(List<Sticker> entities);
}

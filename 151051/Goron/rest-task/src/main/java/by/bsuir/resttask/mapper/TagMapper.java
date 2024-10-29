package by.bsuir.resttask.mapper;

import by.bsuir.resttask.dto.request.TagRequestTo;
import by.bsuir.resttask.dto.response.TagResponseTo;
import by.bsuir.resttask.entity.Tag;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toEntity(TagRequestTo request);
    TagResponseTo toResponseTo(Tag entity);
    Tag updateEntity(@MappingTarget Tag entityToUpdate, TagRequestTo updateRequest);
}

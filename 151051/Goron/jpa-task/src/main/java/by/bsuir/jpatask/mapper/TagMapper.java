package by.bsuir.jpatask.mapper;

import by.bsuir.jpatask.dto.request.TagRequestTo;
import by.bsuir.jpatask.dto.response.TagResponseTo;
import by.bsuir.jpatask.entity.Tag;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toEntity(TagRequestTo request);
    TagResponseTo toResponseTo(Tag entity);
    Tag updateEntity(@MappingTarget Tag entityToUpdate, TagRequestTo updateRequest);
}

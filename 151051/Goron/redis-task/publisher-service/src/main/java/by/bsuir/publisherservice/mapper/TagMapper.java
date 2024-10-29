package by.bsuir.publisherservice.mapper;

import by.bsuir.publisherservice.dto.request.TagRequestTo;
import by.bsuir.publisherservice.dto.response.TagResponseTo;
import by.bsuir.publisherservice.entity.Tag;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "id", ignore = true)
    Tag toEntity(TagRequestTo request);

    TagResponseTo toResponseTo(Tag entity);
    
    @Mapping(target = "id", ignore = true)
    Tag updateEntity(@MappingTarget Tag entityToUpdate, TagRequestTo updateRequest);
}

package by.bsuir.resttask.mapper;

import by.bsuir.resttask.dto.request.NewsRequestTo;
import by.bsuir.resttask.dto.response.NewsResponseTo;
import by.bsuir.resttask.entity.News;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "timeCreated", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "timeModified", expression = "java(java.time.LocalDateTime.now())")
    News toEntity(NewsRequestTo request);

    NewsResponseTo toResponseTo(News entity);
    
    @Mapping(target = "timeCreated", ignore = true)
    @Mapping(target = "timeModified", expression = "java(java.time.LocalDateTime.now())")
    News updateEntity(@MappingTarget News entityToUpdate, NewsRequestTo updateRequest);
}

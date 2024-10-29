package by.bsuir.jpatask.mapper;

import by.bsuir.jpatask.dto.request.NewsRequestTo;
import by.bsuir.jpatask.dto.response.NewsResponseTo;
import by.bsuir.jpatask.entity.Author;
import by.bsuir.jpatask.entity.News;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "author", expression = "java(authorFromRequest)")
    @Mapping(target = "timeCreated", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "timeModified", expression = "java(java.time.LocalDateTime.now())")
    News toEntity(NewsRequestTo request, @Context Author authorFromRequest);

    @Mapping(target = "authorId", source = "author.id")
    NewsResponseTo toResponseTo(News entity);

    @Mapping(target = "author", expression = "java(authorFromUpdateRequest)")
    @Mapping(target = "timeCreated", ignore = true)
    @Mapping(target = "timeModified", expression = "java(java.time.LocalDateTime.now())")
    News updateEntity(@MappingTarget News entityToUpdate, NewsRequestTo updateRequest,
                      @Context Author authorFromUpdateRequest);
}

package by.bsuir.publisherservice.mapper;

import by.bsuir.publisherservice.dto.request.NewsRequestTo;
import by.bsuir.publisherservice.dto.response.NewsResponseTo;
import by.bsuir.publisherservice.entity.Author;
import by.bsuir.publisherservice.entity.News;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", expression = "java(authorFromRequest)")
    @Mapping(target = "timeCreated", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "timeModified", expression = "java(java.time.LocalDateTime.now())")
    News toEntity(NewsRequestTo request, @Context Author authorFromRequest);

    @Mapping(target = "authorId", source = "author.id")
    NewsResponseTo toResponseTo(News entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", expression = "java(authorFromUpdateRequest)")
    @Mapping(target = "timeCreated", ignore = true)
    @Mapping(target = "timeModified", expression = "java(java.time.LocalDateTime.now())")
    News updateEntity(@MappingTarget News entityToUpdate, NewsRequestTo updateRequest,
                      @Context Author authorFromUpdateRequest);
}

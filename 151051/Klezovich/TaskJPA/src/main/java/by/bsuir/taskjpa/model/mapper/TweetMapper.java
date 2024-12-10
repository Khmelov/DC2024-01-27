package by.bsuir.taskjpa.model.mapper;

import by.bsuir.taskjpa.model.dto.request.TweetRequestTo;
import by.bsuir.taskjpa.model.dto.response.TweetResponseTo;
import by.bsuir.taskjpa.model.entity.Tweet;
import by.bsuir.taskjpa.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TweetMapper {

    @Mapping(target = "user", expression = "java(userFromRequest)")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    Tweet toEntity(TweetRequestTo request, @Context User userFromRequest);

    @Mapping(target = "user", expression = "java(userFromUpdateRequest)")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    Tweet updateEntity(@MappingTarget Tweet entityToUpdate, TweetRequestTo updateRequest, @Context User userFromUpdateRequest);

    @Mapping(target = "userId", source = "user.id")
    TweetResponseTo toResponseTo(Tweet entity);
}

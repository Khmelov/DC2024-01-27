package by.bsuir.publisher.model.mapper;

import by.bsuir.publisher.model.dto.request.TweetRequestTo;
import by.bsuir.publisher.model.dto.response.TweetResponseTo;
import by.bsuir.publisher.model.entity.Tweet;
import by.bsuir.publisher.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TweetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", expression = "java(userFromRequest)")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    Tweet toEntity(TweetRequestTo request, @Context User userFromRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", expression = "java(userFromUpdateRequest)")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    Tweet updateEntity(@MappingTarget Tweet entityToUpdate, TweetRequestTo updateRequest, @Context User userFromUpdateRequest);

    @Mapping(target = "userId", source = "user.id")
    TweetResponseTo toResponseTo(Tweet entity);
}

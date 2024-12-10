package by.bsuir.publisher.model.mapper;

import by.bsuir.publisher.model.dto.request.UserRequestTo;
import by.bsuir.publisher.model.dto.response.UserResponseTo;
import by.bsuir.publisher.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestTo request);

    @Mapping(target = "id", ignore = true)
    User updateEntity(@MappingTarget User entityToUpdate, UserRequestTo updateRequest);

    UserResponseTo toResponseTo(User entity);
}

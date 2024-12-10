package by.bsuir.taskjpa.model.mapper;

import by.bsuir.taskjpa.model.dto.request.UserRequestTo;
import by.bsuir.taskjpa.model.dto.response.UserResponseTo;
import by.bsuir.taskjpa.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRequestTo request);
    User updateEntity(@MappingTarget User entityToUpdate, UserRequestTo updateRequest);
    UserResponseTo toResponseTo(User entity);
}

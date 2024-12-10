package by.bsuir.taskrest.model.mapper;

import by.bsuir.taskrest.model.dto.request.UserRequestTo;
import by.bsuir.taskrest.model.dto.response.UserResponseTo;
import by.bsuir.taskrest.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "firstName", source = "firstname")
    @Mapping(target = "lastName", source = "lastname")
    User toEntity(UserRequestTo request);

    @Mapping(target = "firstname", source = "firstName")
    @Mapping(target = "lastname", source = "lastName")
    UserResponseTo toResponseTo(User entity);
    List<UserResponseTo> toResponseTo(List<User> entities);
}

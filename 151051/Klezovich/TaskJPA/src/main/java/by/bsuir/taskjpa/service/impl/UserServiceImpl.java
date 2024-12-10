package by.bsuir.taskjpa.service.impl;

import by.bsuir.taskjpa.exception.EntityNotFoundException;
import by.bsuir.taskjpa.exception.EntitySavingException;
import by.bsuir.taskjpa.model.dto.request.UserRequestTo;
import by.bsuir.taskjpa.model.dto.response.UserResponseTo;
import by.bsuir.taskjpa.model.mapper.UserMapper;
import by.bsuir.taskjpa.repository.UserRepository;
import by.bsuir.taskjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final String entityName = "User";

    @Override
    public UserResponseTo save(UserRequestTo userRequestTo) {
        return Optional.of(userRequestTo)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntitySavingException(entityName, userRequestTo.id()));
    }

    @Override
    public List<UserResponseTo> findAll(Pageable restriction) {
        return repository.findAll(restriction).stream().map(mapper::toResponseTo).toList();
    }

    @Override
    public UserResponseTo findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(entityName, id));
    }

    @Override
    public UserResponseTo update(UserRequestTo userRequestTo) {
        return repository.findById(userRequestTo.id())
                .map(entity -> {
                    if (userRequestTo.login() != null) {
                        entity.setLogin(userRequestTo.login());
                    }
                    if (userRequestTo.password() != null) {
                        entity.setPassword(userRequestTo.password());
                    }
                    if (userRequestTo.firstname() != null) {
                        entity.setFirstname(userRequestTo.firstname());
                    }
                    if (userRequestTo.lastname() != null) {
                        entity.setLastname(userRequestTo.lastname());
                    }
                    return entity;
                })
                .map(repository::save)
                .map(mapper::toResponseTo)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", userRequestTo.id())));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new EntityNotFoundException(entityName, id); });
    }
}

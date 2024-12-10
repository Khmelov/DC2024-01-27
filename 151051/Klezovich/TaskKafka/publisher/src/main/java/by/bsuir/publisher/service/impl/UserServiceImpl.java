package by.bsuir.publisher.service.impl;

import by.bsuir.publisher.exception.EntityNotFoundException;
import by.bsuir.publisher.exception.EntitySavingException;
import by.bsuir.publisher.model.dto.request.UserRequestTo;
import by.bsuir.publisher.model.dto.response.UserResponseTo;
import by.bsuir.publisher.model.mapper.UserMapper;
import by.bsuir.publisher.repository.UserRepository;
import by.bsuir.publisher.service.UserService;
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
                .map(entityToUpdate -> mapper.updateEntity(entityToUpdate, userRequestTo))
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

package by.bsuir.publisher.service.impl;

import by.bsuir.publisher.client.discussion.DiscussionClient;
import by.bsuir.publisher.client.discussion.model.mapper.DiscussionMapper;
import by.bsuir.publisher.exception.EntityNotFoundException;
import by.bsuir.publisher.exception.EntitySavingException;
import by.bsuir.publisher.model.dto.request.NoteRequestTo;
import by.bsuir.publisher.model.dto.response.NoteResponseTo;
import by.bsuir.publisher.repository.TweetRepository;
import by.bsuir.publisher.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final DiscussionClient repository;
    private final TweetRepository tweetRepository;
    private final DiscussionMapper mapper;
    private final String entityName = "Note";

    @Override
    public NoteResponseTo save(NoteRequestTo noteRequestTo, String country) {

        if (!tweetRepository.existsById(noteRequestTo.tweetId())) {
            throw new EntityNotFoundException(entityName, noteRequestTo.tweetId());
        }

        return Optional.of(noteRequestTo)
                .map(request -> mapper.toRequestTo(request, country))
                .map(repository::save)
                .orElseThrow(() -> new EntitySavingException(entityName, noteRequestTo.id()));
    }

    @Override
    public List<NoteResponseTo> findAll(Pageable restriction) {
        return repository.findAll(restriction.getPageNumber(), restriction.getPageSize());
    }

    @Override
    public NoteResponseTo findById(Long id) {
        try {
            return repository.findById(id);
        } catch (HttpClientErrorException e) {
            throw new EntityNotFoundException(entityName, id);
        }
    }

    @Override
    public NoteResponseTo update(NoteRequestTo noteRequestTo, String country) {
        findById(noteRequestTo.id());
        if (!tweetRepository.existsById(noteRequestTo.tweetId())) {
            throw new EntityNotFoundException(entityName, noteRequestTo.tweetId());
        }
        return Optional.of(noteRequestTo)
                .map(entityToUpdate -> mapper.toRequestTo(entityToUpdate, country))
                .map(repository::update)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityName + " with id %s not found", noteRequestTo.id())));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.delete(id);
    }
}

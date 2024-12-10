package by.bsuir.taskrest.service;

import by.bsuir.taskrest.model.dto.request.TweetRequestTo;
import by.bsuir.taskrest.model.dto.response.TweetResponseTo;

import java.util.List;

public interface TweetService {
    TweetResponseTo save(TweetRequestTo entity);
    List<TweetResponseTo> findAll();
    TweetResponseTo findById(Long id);
    TweetResponseTo update(TweetRequestTo updateRequest);
    void delete(Long id);
}

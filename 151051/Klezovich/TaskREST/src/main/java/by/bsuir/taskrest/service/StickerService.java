package by.bsuir.taskrest.service;

import by.bsuir.taskrest.model.dto.request.StickerRequestTo;
import by.bsuir.taskrest.model.dto.response.StickerResponseTo;

import java.util.List;

public interface StickerService {
    StickerResponseTo save(StickerRequestTo entity);
    List<StickerResponseTo> findAll();
    StickerResponseTo findById(Long id);
    StickerResponseTo update(StickerRequestTo updateRequest);
    void delete(Long id);
}

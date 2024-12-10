package by.bsuir.taskjpa.service;

import by.bsuir.taskjpa.model.dto.request.StickerRequestTo;
import by.bsuir.taskjpa.model.dto.response.StickerResponseTo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StickerService {
    StickerResponseTo save(StickerRequestTo entity);
    List<StickerResponseTo> findAll(Pageable restriction);
    StickerResponseTo findById(Long id);
    StickerResponseTo update(StickerRequestTo updateRequest);
    void delete(Long id);
}

package by.bsuir.publisher.services;

import by.bsuir.publisher.dto.requests.StoryRequestDto;
import by.bsuir.publisher.dto.responses.StoryResponseDto;

import java.util.List;

public interface StoryService extends BaseService<StoryRequestDto, StoryResponseDto> {
    List<StoryResponseDto> readAll();
}

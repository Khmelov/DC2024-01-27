package by.bsuir.publisher.services;

import by.bsuir.publisher.dto.requests.LabelRequestDto;
import by.bsuir.publisher.dto.responses.LabelResponseDto;

import java.util.List;

public interface LabelService extends BaseService<LabelRequestDto, LabelResponseDto> {
    List<LabelResponseDto> readAll();
}

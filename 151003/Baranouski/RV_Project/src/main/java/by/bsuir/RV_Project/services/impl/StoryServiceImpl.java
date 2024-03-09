package by.bsuir.RV_Project.services.impl;

import by.bsuir.RV_Project.domain.Story;
import by.bsuir.RV_Project.dto.requests.StoryRequestDto;
import by.bsuir.RV_Project.dto.requests.converters.StoryRequestConverter;
import by.bsuir.RV_Project.dto.responses.StoryResponseDto;
import by.bsuir.RV_Project.dto.responses.converters.CollectionStoryResponseConverter;
import by.bsuir.RV_Project.dto.responses.converters.StoryResponseConverter;
import by.bsuir.RV_Project.exceptions.EntityExistsException;
import by.bsuir.RV_Project.exceptions.Messages;
import by.bsuir.RV_Project.exceptions.NoEntityExistsException;
import by.bsuir.RV_Project.repositories.StoryRepository;
import by.bsuir.RV_Project.services.StoryService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final StoryRequestConverter storyRequestConverter;
    private final StoryResponseConverter storyResponseConverter;
    private final CollectionStoryResponseConverter collectionStoryResponseConverter;

    @Override
    @Validated
    public StoryResponseDto create(@Valid @NonNull StoryRequestDto dto) throws EntityExistsException {
        Optional<Story> story = dto.getId() == null ? Optional.empty() : storyRepository.findById(dto.getId());
        if (story.isEmpty()) {
            return storyResponseConverter.toDto(storyRepository.save(storyRequestConverter.fromDto(dto)));
        } else {
            throw new EntityExistsException(Messages.EntityExistsException);
        }
    }

    @Override
    public Optional<StoryResponseDto> read(@NonNull Long id) {
        return storyRepository.findById(id).flatMap(story -> Optional.of(
                storyResponseConverter.toDto(story)));
    }

    @Override
    @Validated
    public StoryResponseDto update(@Valid @NonNull StoryRequestDto dto) throws NoEntityExistsException {
        Optional<Story> story = dto.getId() == null || storyRepository.findById(dto.getId()).isEmpty() ?
                Optional.empty() : Optional.of(storyRequestConverter.fromDto(dto));
        return storyResponseConverter.toDto(storyRepository.save(story.orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException))));
    }

    @Override
    public Long delete(@NonNull Long id) throws NoEntityExistsException {
        Optional<Story> story = storyRepository.findById(id);
        storyRepository.deleteById(story.map(Story::getId).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
        return story.get().getId();
    }

    @Override
    public List<StoryResponseDto> readAll() {
        return collectionStoryResponseConverter.toListDto(storyRepository.findAll());
    }
}

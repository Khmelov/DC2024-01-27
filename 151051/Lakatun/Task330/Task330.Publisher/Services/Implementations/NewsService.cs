using AutoMapper;
using FluentValidation;
using Task330.Publisher.DTO.RequestDTO;
using Task330.Publisher.DTO.ResponseDTO;
using Task330.Publisher.Exceptions;
using Task330.Publisher.Infrastructure.Validators;
using Task330.Publisher.Models;
using Task330.Publisher.Repositories.Interfaces;
using Task330.Publisher.Services.Interfaces;

namespace Task330.Publisher.Services.Implementations;

public class NewsService(
    INewsRepository newsRepository,
    ICreatorRepository creatorRepository,
    IMapper mapper,
    NewsRequestDtoValidator validator) : INewsService
{
    private readonly ICreatorRepository _creatorRepository = creatorRepository;
    private readonly IMapper _mapper = mapper;
    private readonly INewsRepository _newsRepository = newsRepository;
    private readonly NewsRequestDtoValidator _validator = validator;

    public async Task<IEnumerable<NewsResponseDto>> GetNewsAsync()
    {
        var news = await _newsRepository.GetAllAsync();
        return _mapper.Map<IEnumerable<NewsResponseDto>>(news);
    }

    public async Task<NewsResponseDto> GetNewsByIdAsync(long id)
    {
        var news = await _newsRepository.GetByIdAsync(id)
                   ?? throw new NotFoundException(ErrorMessages.NewsNotFoundMessage(id));
        return _mapper.Map<NewsResponseDto>(news);
    }

    public async Task<NewsResponseDto> CreateNewsAsync(NewsRequestDto news)
    {
        _validator.ValidateAndThrow(news);

        var newsToCreate = _mapper.Map<News>(news);
        newsToCreate.CreatorId = news.CreatorId;
        newsToCreate.Created = DateTime.UtcNow;
        newsToCreate.Modified = DateTime.UtcNow;

        var createdNews = await _newsRepository.CreateAsync(newsToCreate);
        var responseDto = _mapper.Map<NewsResponseDto>(createdNews);
        return responseDto;
    }

    public async Task<NewsResponseDto> UpdateNewsAsync(NewsRequestDto news)
    {
        _validator.ValidateAndThrow(news);
        var newsToUpdate = _mapper.Map<News>(news);
        newsToUpdate.Modified = DateTime.UtcNow;
        var updatedNews = await _newsRepository.UpdateAsync(newsToUpdate)
                          ?? throw new NotFoundException(ErrorMessages.NewsNotFoundMessage(news.Id));
        return _mapper.Map<NewsResponseDto>(updatedNews);
    }

    public async Task DeleteNewsAsync(long id)
    {
        if (!await _newsRepository.DeleteAsync(id)) throw new NotFoundException(ErrorMessages.NewsNotFoundMessage(id));
    }
}
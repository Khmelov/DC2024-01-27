using AutoMapper;
using FluentValidation;
using Task320.DTO.RequestDTO;
using Task320.DTO.ResponseDTO;
using Task320.Exceptions;
using Task320.Infrastructure.Validators;
using Task320.Models;
using Task320.Repositories.Interfaces;
using Task320.Services.Interfaces;

namespace Task320.Services.Implementations
{
    public class NewsService(INewsRepository newsRepository, ICreatorRepository creatorRepository,
        IMapper mapper, NewsRequestDtoValidator validator) : INewsService
    {
        private readonly INewsRepository _newsRepository = newsRepository;
        private readonly ICreatorRepository _creatorRepository = creatorRepository;
        private readonly IMapper _mapper = mapper;
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
            if (!await _newsRepository.DeleteAsync(id))
            {
                throw new NotFoundException(ErrorMessages.NewsNotFoundMessage(id));
            }
        }
    }
}

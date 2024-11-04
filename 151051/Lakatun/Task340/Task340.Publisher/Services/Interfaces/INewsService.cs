using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.DTO.ResponseDTO;

namespace Task340.Publisher.Services.Interfaces;

public interface INewsService
{
    Task<IEnumerable<NewsResponseDto>> GetNewsAsync();

    Task<NewsResponseDto> GetNewsByIdAsync(long id);

    Task<NewsResponseDto> CreateNewsAsync(NewsRequestDto issue);

    Task<NewsResponseDto> UpdateNewsAsync(NewsRequestDto issue);

    Task DeleteNewsAsync(long id);
}
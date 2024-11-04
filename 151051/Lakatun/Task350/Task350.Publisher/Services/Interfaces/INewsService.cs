using Task350.Publisher.DTO.RequestDTO;
using Task350.Publisher.DTO.ResponseDTO;

namespace Task350.Publisher.Services.Interfaces;

public interface INewsService
{
    Task<IEnumerable<NewsResponseDto>> GetNewsAsync();

    Task<NewsResponseDto> GetNewsByIdAsync(long id);

    Task<NewsResponseDto> CreateNewsAsync(NewsRequestDto issue);

    Task<NewsResponseDto> UpdateNewsAsync(NewsRequestDto issue);

    Task DeleteNewsAsync(long id);
}
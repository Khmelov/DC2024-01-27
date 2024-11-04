using Task320.DTO.RequestDTO;
using Task320.DTO.ResponseDTO;

namespace Task320.Services.Interfaces
{
    public interface INewsService
    {
        Task<IEnumerable<NewsResponseDto>> GetNewsAsync();

        Task<NewsResponseDto> GetNewsByIdAsync(long id);

        Task<NewsResponseDto> CreateNewsAsync(NewsRequestDto issue);

        Task<NewsResponseDto> UpdateNewsAsync(NewsRequestDto issue);

        Task DeleteNewsAsync(long id);
    }
}

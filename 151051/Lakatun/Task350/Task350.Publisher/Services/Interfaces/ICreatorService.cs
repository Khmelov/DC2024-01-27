using Task350.Publisher.DTO.RequestDTO;
using Task350.Publisher.DTO.ResponseDTO;

namespace Task350.Publisher.Services.Interfaces;

public interface ICreatorService
{
    Task<IEnumerable<CreatorResponseDto>> GetCreatorsAsync();

    Task<CreatorResponseDto> GetCreatorByIdAsync(long id);

    Task<CreatorResponseDto> CreateCreatorAsync(CreatorRequestDto creator);

    Task<CreatorResponseDto> UpdateCreatorAsync(CreatorRequestDto creator);

    Task DeleteCreatorAsync(long id);
}
using Task330.Publisher.DTO.RequestDTO;
using Task330.Publisher.DTO.ResponseDTO;

namespace Task330.Publisher.Services.Interfaces;

public interface IStickerService
{
    Task<IEnumerable<StickerResponseDto>> GetStickersAsync();

    Task<StickerResponseDto> GetStickerByIdAsync(long id);

    Task<StickerResponseDto> CreateStickerAsync(StickerRequestDto sticker);

    Task<StickerResponseDto> UpdateStickerAsync(StickerRequestDto sticker);

    Task DeleteStickerAsync(long id);
}
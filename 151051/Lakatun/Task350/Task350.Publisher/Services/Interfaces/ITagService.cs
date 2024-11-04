using Task350.Publisher.DTO.RequestDTO;
using Task350.Publisher.DTO.ResponseDTO;

namespace Task350.Publisher.Services.Interfaces;

public interface ITagService
{
    Task<IEnumerable<StickerResponseDto>> GetStickersAsync();

    Task<StickerResponseDto> GetStickerByIdAsync(long id);

    Task<StickerResponseDto> CreateStickerAsync(StickerRequestDto sticker);

    Task<StickerResponseDto> UpdateStickerAsync(StickerRequestDto sticker);

    Task DeleteStickerAsync(long id);
}
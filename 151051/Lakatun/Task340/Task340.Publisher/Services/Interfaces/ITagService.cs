using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.DTO.ResponseDTO;

namespace Task340.Publisher.Services.Interfaces;

public interface ITagService
{
    Task<IEnumerable<StickerResponseDto>> GetStickersAsync();

    Task<StickerResponseDto> GetStickerByIdAsync(long id);

    Task<StickerResponseDto> CreateStickerAsync(StickerRequestDto sticker);

    Task<StickerResponseDto> UpdateStickerAsync(StickerRequestDto sticker);

    Task DeleteStickerAsync(long id);
}
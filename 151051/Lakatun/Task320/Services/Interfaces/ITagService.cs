using Task320.DTO.RequestDTO;
using Task320.DTO.ResponseDTO;

namespace Task320.Services.Interfaces
{
    public interface IStickerService
    {
        Task<IEnumerable<StickerResponseDto>> GetStickersAsync();

        Task<StickerResponseDto> GetStickerByIdAsync(long id);

        Task<StickerResponseDto> CreateStickerAsync(StickerRequestDto sticker);

        Task<StickerResponseDto> UpdateStickerAsync(StickerRequestDto sticker);

        Task DeleteStickerAsync(long id);
    }
}

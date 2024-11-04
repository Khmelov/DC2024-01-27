using Asp.Versioning;
using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task340.Publisher.Controllers.V1;

[Route("api/v{version:apiVersion}/stickers")]
[ApiController]
[ApiVersion("1.0")]
public class TagController(ITagService tagService) : ControllerBase
{
    [HttpGet]
    public async Task<IActionResult> GetTagss()
    {
        var stickers = await stickerService.GetStickersAsync();
        return Ok(stickers);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetStickerById(long id)
    {
        var sticker = await stickerService.GetStickerByIdAsync(id);
        return Ok(sticker);
    }

    [HttpPost]
    public async Task<IActionResult> CreateSticker([FromBody] StickerRequestDto sticker)
    {
        var createdSticker = await stickerService.CreateStickerAsync(sticker);
        return CreatedAtAction(nameof(GetStickerById), new { id = createdSticker.Id }, createdSticker);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateSticker([FromBody] StickerRequestDto sticker)
    {
        var updatedSticker = await stickerService.UpdateStickerAsync(sticker);
        return Ok(updatedSticker);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteSticker(long id)
    {
        await stickerService.DeleteStickerAsync(id);
        return NoContent();
    }
}
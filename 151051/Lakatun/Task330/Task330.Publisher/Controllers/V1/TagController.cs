using Asp.Versioning;
using Task330.Publisher.DTO.RequestDTO;
using Task330.Publisher.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task330.Publisher.Controllers.V1;

[Route("api/v{version:apiVersion}/tags")]
[ApiController]
[ApiVersion("1.0")]
public class TagsController(ITagService tagService) : ControllerBase
{
    [HttpGet]
    public async Task<IActionResult> GetTags()
    {
        var tag = await tagService.GetTagsAsync();
        return Ok(tags);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetTagsById(long id)
    {
        var tag = await tagsService.GetTagByIdAsync(id);
        return Ok(tag);
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
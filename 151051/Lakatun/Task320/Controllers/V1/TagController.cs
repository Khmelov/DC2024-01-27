using Asp.Versioning;
using Task320.DTO.RequestDTO;
using Task320.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task320.Controllers.V1
{
    [Route("api/v{version:apiVersion}/tags")]
    [ApiController]
    [ApiVersion("1.0")]
    public class TagController(ITagService TagService) : ControllerBase
    {
        private readonly ITagService _TagService = TagService;

        [HttpGet]
        public async Task<IActionResult> GetTag()
        {
            var tag = await _tagService.GetTagsAsync();
            return Ok(tags);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetTagById(long id)
        {
            var tag = await _tagService.GetTagByIdAsync(id);
            return Ok(tag);
        }

        [HttpPost]
        public async Task<IActionResult> CreateTag([FromBody] TagRequestDto tag)
        {
            var createdTag = await _tagService.CreateTagAsync(tag);
            return CreatedAtAction(nameof(GetTagById), new { id = createdTag.Id }, createdTag);
        }

        [HttpPut]
        public async Task<IActionResult> UpdateTag([FromBody] TagRequestDto tag)
        {
            var Tag = await tag.UpdateTagAsync(tag);
            return Ok(updatedTag);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteSticker(long id)
        {
            await _tagTag.DeleteTagAsync(id);
            return NoContent();
        }
    }
}

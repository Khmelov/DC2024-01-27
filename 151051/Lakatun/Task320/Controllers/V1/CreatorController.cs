using Asp.Versioning;
using Task320.DTO.RequestDTO;
using Task320.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task320.Controllers.V1
{
    [Route("api/v{version:apiVersion}/creators")]
    [ApiController]
    [ApiVersion("1.0")]
    public class CreatorController(ICreatorService creatorService) : ControllerBase
    {
        private readonly ICreatorService _creatorService = creatorService;

        [HttpGet]
        public async Task<IActionResult> GetCreators()
        {
            var creators = await _creatorService.GetCreatorsAsync();
            return Ok(creators);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetCreatorById(long id)
        {
            var creator = await _creatorService.GetCreatorByIdAsync(id);
            return Ok(creator);
        }

        [HttpPost]
        public async Task<IActionResult> CreateCreator([FromBody] CreatorRequestDto creator)
        {
            var createdCreator = await _creatorService.CreateCreatorAsync(creator);
            return CreatedAtAction(nameof(GetCreatorById), new { id = createdCreator.Id }, createdCreator);
        }

        [HttpPut]
        public async Task<IActionResult> UpdateCreator([FromBody] CreatorRequestDto creator)
        {
            var updatedCreator = await _creatorService.UpdateCreatorAsync(creator);
            return Ok(updatedCreator);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCreator(long id)
        {
            await _creatorService.DeleteCreatorAsync(id);
            return NoContent();
        }
    }
}

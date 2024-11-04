using Asp.Versioning;
using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task340.Publisher.Controllers.V1;

[Route("api/v{version:apiVersion}/creators")]
[ApiController]
[ApiVersion("1.0")]
public class CreatorController(ICreatorService creatorService) : ControllerBase
{
    [HttpGet]
    public async Task<IActionResult> GetCreators()
    {
        var creators = await creatorService.GetCreatorsAsync();
        return Ok(creators);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetCreatorById(long id)
    {
        var creator = await creatorService.GetCreatorByIdAsync(id);
        return Ok(creator);
    }

    [HttpPost]
    public async Task<IActionResult> CreateCreator([FromBody] CreatorRequestDto creator)
    {
        var createdCreator = await creatorService.CreateCreatorAsync(creator);
        return CreatedAtAction(nameof(GetCreatorById), new { id = createdCreator.Id }, createdCreator);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateCreator([FromBody] CreatorRequestDto creator)
    {
        var updatedCreator = await creatorService.UpdateCreatorAsync(creator);
        return Ok(updatedCreator);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteCreator(long id)
    {
        await creatorService.DeleteCreatorAsync(id);
        return NoContent();
    }
}
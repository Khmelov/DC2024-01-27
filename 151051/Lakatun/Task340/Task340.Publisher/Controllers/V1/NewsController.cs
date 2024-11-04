using Asp.Versioning;
using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Lab4.Publisher.Controllers.V1;

[Route("api/v{version:apiVersion}/news")]
[ApiController]
[ApiVersion("1.0")]
public class NewsController(INewsService newsService) : ControllerBase
{
    [HttpGet]
    public async Task<IActionResult> GetNews()
    {
        var news = await newsService.GetNewsAsync();
        return Ok(news);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetNewsById(long id)
    {
        var news = await newsService.GetNewsByIdAsync(id);
        return Ok(news);
    }

    [HttpPost]
    public async Task<IActionResult> CreateNews([FromBody] NewsRequestDto news)
    {
        var createdNews = await newsService.CreateNewsAsync(news);
        return CreatedAtAction(nameof(GetNewsById), new { id = createdNews.Id }, createdNews);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateNews([FromBody] NewsRequestDto news)
    {
        var updatedNews = await newsService.UpdateNewsAsync(news);
        return Ok(updatedNews);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteNews(long id)
    {
        await newsService.DeleteNewsAsync(id);
        return NoContent();
    }
}
using Asp.Versioning;
using Task320.DTO.RequestDTO;
using Task320.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task320.Controllers.V1
{
    [Route("api/v{version:apiVersion}/news")]
    [ApiController]
    [ApiVersion("1.0")]
    public class NewsController(INewsService newsService) : ControllerBase
    {
        private readonly INewsService _newsService = newsService;

        [HttpGet]
        public async Task<IActionResult> GetNews()
        {
            var news = await _newsService.GetNewsAsync();
            return Ok(news);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetNewsById(long id)
        {
            var news = await _newsService.GetNewsByIdAsync(id);
            return Ok(news);
        }

        [HttpPost]
        public async Task<IActionResult> CreateNews([FromBody] NewsRequestDto news)
        {
            var createdNews = await _newsService.CreateNewsAsync(news);
            return CreatedAtAction(nameof(GetNewsById), new { id = createdNews.Id }, createdNews);
        }

        [HttpPut]
        public async Task<IActionResult> UpdateNews([FromBody] NewsRequestDto news)
        {
            var updatedNews = await _newsService.UpdateNewsAsync(news);
            return Ok(updatedNews);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNews(long id)
        {
            await _newsService.DeleteNewsAsync(id);
            return NoContent();
        }
    }
}

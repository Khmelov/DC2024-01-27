using Asp.Versioning;
using Task350.Publisher.DTO.RequestDTO;
using Task350.Publisher.DTO.ResponseDTO;
using Task350.Publisher.HttpClients.Interfaces;
using Task350.Publisher.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Distributed;
using Newtonsoft.Json;

namespace Task350.Publisher.Controllers.V1;

[Route("api/v{version:apiVersion}/notes")]
[ApiController]
[ApiVersion("1.0")]
public class NoteController(IDiscussionClient noteClient, INewsService newsService, IDistributedCache redisCache) : ControllerBase
{
    [HttpGet]
    public async Task<IActionResult> GetNotes()
    {
        var cachedNotes = await redisCache.GetStringAsync("Notes:GetNotes");
        if (!string.IsNullOrEmpty(cachedNotes))
        {
            return Ok(JsonConvert.DeserializeObject<List<NoteResponseDto>>(cachedNotes));
        }

        var notes = await noteClient.GetNotesAsync();

        await redisCache.SetStringAsync("Notes:GetNotes", JsonConvert.SerializeObject(notes), new DistributedCacheEntryOptions
        {
            AbsoluteExpirationRelativeToNow = TimeSpan.FromMinutes(10)
        });

        return Ok(notes);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetNoteById(long id)
    {
        var cachedNote = await redisCache.GetStringAsync($"Notes:GetNoteById:{id}");
        if (!string.IsNullOrEmpty(cachedNote))
        {
            return Ok(JsonConvert.DeserializeObject<NoteResponseDto>(cachedNote));
        }

        var note = await noteClient.GetNoteByIdAsync(id);

        await redisCache.SetStringAsync($"Notes:GetNoteById:{id}", JsonConvert.SerializeObject(note), new DistributedCacheEntryOptions
        {
            AbsoluteExpirationRelativeToNow = TimeSpan.FromMinutes(10)
        });

        return Ok(note);
    }

    [HttpPost]
    public async Task<IActionResult> CreateNote([FromBody] NoteRequestDto note)
    {
        var news = await newsService.GetNewsByIdAsync(note.NewsId);
        var createdNote = await noteClient.CreateNoteAsync(note);

        await redisCache.RemoveAsync("Notes:GetNotes");

        return CreatedAtAction(nameof(GetNoteById), new { id = createdNote.Id }, createdNote);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateNote([FromBody] NoteRequestDto note)
    {
        var updatedNote = await noteClient.UpdateNoteAsync(note);

        await redisCache.RemoveAsync("Notes:GetNotes");
        await redisCache.RemoveAsync($"Notes:GetNoteById:{updatedNote.Id}");

        return Ok(updatedNote);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteNote(long id)
    {
        await noteClient.DeleteNoteAsync(id);

        await redisCache.RemoveAsync("Notes:GetNotes");
        await redisCache.RemoveAsync($"Notes:GetNoteById:{id}");

        return NoContent();
    }
}
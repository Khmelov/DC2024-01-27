using Asp.Versioning;
using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.HttpClients.Interfaces;
using Task340.Publisher.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task340.Publisher.Controllers.V1;

[Route("api/v{version:apiVersion}/notes")]
[ApiController]
[ApiVersion("1.0")]
public class NoteController(IDiscussionClient noteClient, INewsService newsService) : ControllerBase
{
    [HttpGet]
    public async Task<IActionResult> GetNotes()
    {
        var notes = await noteClient.GetNotesAsync();
        return Ok(notes);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetNoteById(long id)
    {
        var note = await noteClient.GetNoteByIdAsync(id);
        return Ok(note);
    }

    [HttpPost]
    public async Task<IActionResult> CreateNote([FromBody] NoteRequestDto note)
    {
        var news = await newsService.GetNewsByIdAsync(note.NewsId);
        var createdNote = await noteClient.CreateNoteAsync(note);
        return CreatedAtAction(nameof(GetNoteById), new { id = createdNote.Id }, createdNote);
    }

    [HttpPut]
    public async Task<IActionResult> UpdateNote([FromBody] NoteRequestDto note)
    {
        var updatedNote = await noteClient.UpdateNoteAsync(note);
        return Ok(updatedNote);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteNote(long id)
    {
        await noteClient.DeleteNoteAsync(id);
        return NoContent();
    }
}
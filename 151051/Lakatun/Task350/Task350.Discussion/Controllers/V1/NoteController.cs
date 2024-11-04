using Asp.Versioning;
using Task340.Discussion.DTO.RequestDTO;
using Task340.Discussion.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace Task340.Discussion.Controllers.V1
{
    [Route("api/v{version:apiVersion}/notes")]
    [ApiController]
    [ApiVersion("1.0")]
    public class NoteController(INoteService noteService) : ControllerBase
    {
        private readonly INoteService _noteService = noteService;

        [HttpGet]
        public async Task<IActionResult> GetNotes()
        {
            var notes = await _noteService.GetNotesAsync();
            return Ok(notes);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetNoteById(long id)
        {
            var note = await _noteService.GetNoteByIdAsync(id);
            return Ok(note);
        }

        [HttpPost]
        public async Task<IActionResult> CreateNote([FromBody] NoteRequestDto post)
        {
            var createdNote = await _noteService.CreateNoteAsync(post);
            return CreatedAtAction(nameof(GetNoteById), new { id = createdNote.Id }, createdNote);
        }

        [HttpPut]
        public async Task<IActionResult> UpdateNote([FromBody] NoteRequestDto post)
        {
            var updatedNote = await _noteService.UpdateNoteAsync(post);
            return Ok(updatedNote);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNote(long id)
        {
            await _noteService.DeleteNoteAsync(id);
            return NoContent();
        }
    }
}

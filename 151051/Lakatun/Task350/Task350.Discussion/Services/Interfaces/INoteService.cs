using Task340.Discussion.DTO.RequestDTO;
using Task340.Discussion.DTO.ResponseDTO;

namespace Task340.Discussion.Services.Interfaces
{
    public interface INoteService
    {
        Task<IEnumerable<NoteResponseDto>> GetNotesAsync();

        Task<NoteResponseDto> GetNoteByIdAsync(long id);

        Task<NoteResponseDto> CreateNoteAsync(NoteRequestDto post);

        Task<NoteResponseDto> UpdateNoteAsync(NoteRequestDto post);

        Task DeleteNoteAsync(long id);
    }
}

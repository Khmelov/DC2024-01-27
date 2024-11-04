using AutoMapper;
using FluentValidation;
using Task330.Discussion.DTO.RequestDTO;
using Task330.Discussion.DTO.ResponseDTO;
using Task330.Discussion.Exceptions;
using Task330.Discussion.Infrastructure.Validators;
using Task330.Discussion.Models;
using Task330.Discussion.Repositories.Interfaces;
using Task330.Discussion.Services.Interfaces;

namespace Task330.Discussion.Services.Implementations
{
    public class NoteService(ICassandraRepository<Note> noteRepository,
        IMapper mapper, NoteRequestDtoValidator validator) : INoteService
    {
        public async Task<IEnumerable<NoteResponseDto>> GetNotesAsync()
        {
            var notes = await noteRepository.GetAllAsync();
            return mapper.Map<IEnumerable<NoteResponseDto>>(notes);
        }

        public async Task<NoteResponseDto> GetNoteByIdAsync(long id)
        {
            var note = await noteRepository.GetByIdAsync(id)
                ?? throw new NotFoundException(ErrorMessages.NoteNotFoundMessage(id));
            return mapper.Map<NoteResponseDto>(note);
        }

        public async Task<NoteResponseDto> CreateNoteAsync(NoteRequestDto note)
        {
            validator.ValidateAndThrow(note);
            var noteToCreate = mapper.Map<Note>(note);
             noteToCreate.NewsId = note.NewsId;
            var createdNote = await noteRepository.CreateAsync(noteToCreate);
            return mapper.Map<NoteResponseDto>(createdNote);
        }

        public async Task<NoteResponseDto> UpdateNoteAsync(NoteRequestDto note)
        {
            validator.ValidateAndThrow(note);
            var noteToUpdate = mapper.Map<Note>(note);
            var updatedNote = await noteRepository.UpdateAsync(noteToUpdate)
                ?? throw new NotFoundException(ErrorMessages.NoteNotFoundMessage(note.Id));
            return mapper.Map<NoteResponseDto>(updatedNote);
        }

        public async Task DeleteNoteAsync(long id)
        {
            if (!await noteRepository.DeleteAsync(id))
            {
                throw new NotFoundException(ErrorMessages.NoteNotFoundMessage(id));
            }
        }
    }
}

using AutoMapper;
using FluentValidation;
using Task320.DTO.RequestDTO;
using Task320.DTO.ResponseDTO;
using Task320.Exceptions;
using Task320.Infrastructure.Validators;
using Task320.Models;
using Task320.Repositories.Interfaces;
using Task320.Services.Interfaces;

namespace Task320.Services.Implementations
{
    public class NoteService(INoteRepository noteRepository,
        IMapper mapper, NoteRequestDtoValidator validator) : INoteService
    {
        private readonly INoteRepository _noteRepository = noteRepository;
        private readonly IMapper _mapper = mapper;
        private readonly NoteRequestDtoValidator _validator = validator;

        public async Task<IEnumerable<NoteResponseDto>> GetNotesAsync()
        {
            var notes = await _noteRepository.GetAllAsync();
            return _mapper.Map<IEnumerable<NoteResponseDto>>(notes);
        }

        public async Task<NoteResponseDto> GetNoteByIdAsync(long id)
        {
            var note = await _noteRepository.GetByIdAsync(id)
                ?? throw new NotFoundException(ErrorMessages.NoteNotFoundMessage(id));
            return _mapper.Map<NoteResponseDto>(note);
        }

        public async Task<NoteResponseDto> CreateNoteAsync(NoteRequestDto note)
        {
            _validator.ValidateAndThrow(note);
            var noteToCreate = _mapper.Map<Note>(note);
             noteToCreate.NewsId = note.NewsId;
            var createdNote = await _noteRepository.CreateAsync(noteToCreate);
            return _mapper.Map<NoteResponseDto>(createdNote);
        }

        public async Task<NoteResponseDto> UpdateNoteAsync(NoteRequestDto note)
        {
            _validator.ValidateAndThrow(note);
            var noteToUpdate = _mapper.Map<Note>(note);
            var updatedNote = await _noteRepository.UpdateAsync(noteToUpdate)
                ?? throw new NotFoundException(ErrorMessages.NoteNotFoundMessage(note.Id));
            return _mapper.Map<NoteResponseDto>(updatedNote);
        }

        public async Task DeleteNoteAsync(long id)
        {
            if (!await _noteRepository.DeleteAsync(id))
            {
                throw new NotFoundException(ErrorMessages.NoteNotFoundMessage(id));
            }
        }

    }
}

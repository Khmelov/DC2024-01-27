using FluentValidation;
using Task340.Discussion.DTO.RequestDTO;

namespace Task340.Discussion.Infrastructure.Validators
{
    public class NoteRequestDtoValidator : AbstractValidator<NoteRequestDto>
    {
        public NoteRequestDtoValidator()
        {
            RuleFor(dto => dto.Content).Length(2, 2048);
        }
    }
}

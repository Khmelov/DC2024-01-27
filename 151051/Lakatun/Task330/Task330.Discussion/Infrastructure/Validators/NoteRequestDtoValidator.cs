using FluentValidation;
using Task330.Discussion.DTO.RequestDTO;

namespace Task330.Discussion.Infrastructure.Validators
{
    public class NoteRequestDtoValidator : AbstractValidator<NoteRequestDto>
    {
        public NoteRequestDtoValidator()
        {
            RuleFor(dto => dto.Content).Length(2, 2048);
        }
    }
}

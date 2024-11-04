using FluentValidation;
using Task330.Publisher.DTO.RequestDTO;

namespace Task330.Publisher.Infrastructure.Validators;

public class NoteRequestDtoValidator : AbstractValidator<NoteRequestDto>
{
    public NoteRequestDtoValidator()
    {
        RuleFor(dto => dto.Content).Length(2, 2048);
    }
}
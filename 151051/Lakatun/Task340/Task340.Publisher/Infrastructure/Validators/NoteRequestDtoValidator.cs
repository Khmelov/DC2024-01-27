using FluentValidation;
using Task340.Publisher.DTO.RequestDTO;

namespace Task340.Publisher.Infrastructure.Validators;

public class NoteRequestDtoValidator : AbstractValidator<NoteRequestDto>
{
    public NoteRequestDtoValidator()
    {
        RuleFor(dto => dto.Content).Length(2, 2048);
    }
}
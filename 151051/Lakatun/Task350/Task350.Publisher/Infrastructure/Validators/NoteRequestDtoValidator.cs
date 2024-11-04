using FluentValidation;
using Task350.Publisher.DTO.RequestDTO;

namespace Task350.Publisher.Infrastructure.Validators;

public class NoteRequestDtoValidator : AbstractValidator<NoteRequestDto>
{
    public NoteRequestDtoValidator()
    {
        RuleFor(dto => dto.Content).Length(2, 2048);
    }
}
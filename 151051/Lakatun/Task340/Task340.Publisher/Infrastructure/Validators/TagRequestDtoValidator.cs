using FluentValidation;
using Task340.Publisher.DTO.RequestDTO;

namespace Task340.Publisher.Infrastructure.Validators;

public class TagRequestDtoValidator : AbstractValidator<TagRequestDto>
{
    public TagRequestDtoValidator()
    {
        RuleFor(dto => dto.Name).Length(2, 32);
    }
}
using FluentValidation;
using Task350.Publisher.DTO.RequestDTO;

namespace Task350.Publisher.Infrastructure.Validators;

public class TagRequestDtoValidator : AbstractValidator<TagRequestDto>
{
    public TagRequestDtoValidator()
    {
        RuleFor(dto => dto.Name).Length(2, 32);
    }
}
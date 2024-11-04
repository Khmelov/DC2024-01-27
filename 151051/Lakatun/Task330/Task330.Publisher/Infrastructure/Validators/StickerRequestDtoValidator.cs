using FluentValidation;
using Task330.Publisher.DTO.RequestDTO;

namespace Task330.Publisher.Infrastructure.Validators;

public class TagRequestDtoValidator : AbstractValidator<StickerRequestDto>
{
    public TagRequestDtoValidator()
    {
        RuleFor(dto => dto.Name).Length(2, 32);
    }
}
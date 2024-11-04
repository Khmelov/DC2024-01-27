using FluentValidation;
using Task320.DTO.RequestDTO;

namespace Task320.Infrastructure.Validators
{
    public class TagRequestDtoValidator : AbstractValidator<TagRequestDto>
    {
        public TagRequestDtoValidator()
        {
            RuleFor(dto => dto.Name).Length(2, 32);
        }
    }
}

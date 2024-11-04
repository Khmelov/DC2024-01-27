using FluentValidation;
using Task350.Publisher.DTO.RequestDTO;

namespace Task350.Publisher.Infrastructure.Validators;

public class CreatorRequestDtoValidator : AbstractValidator<CreatorRequestDto>
{
    public CreatorRequestDtoValidator()
    {
        RuleFor(dto => dto.Login).Length(2, 64);
        RuleFor(dto => dto.Password).Length(8, 128);
        RuleFor(dto => dto.Firstname).Length(2, 64);
        RuleFor(dto => dto.Lastname).Length(2, 64);
    }
}
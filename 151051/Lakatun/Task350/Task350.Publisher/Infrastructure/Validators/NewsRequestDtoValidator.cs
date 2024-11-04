using FluentValidation;
using Task350.Publisher.DTO.RequestDTO;

namespace Task350.Publisher.Infrastructure.Validators;

public class NewsRequestDtoValidator : AbstractValidator<NewsRequestDto>
{
    public NewsRequestDtoValidator()
    {
        RuleFor(dto => dto.Title).Length(2, 64);
        RuleFor(dto => dto.Content).Length(4, 2048);
    }
}
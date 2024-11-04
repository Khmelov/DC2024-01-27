using FluentValidation;
using Task320.DTO.RequestDTO;

namespace Task320.Infrastructure.Validators
{
    public class NewsRequestDtoValidator : AbstractValidator<NewsRequestDto>
    {
        public NewsRequestDtoValidator()
        {
            RuleFor(dto => dto.Title).Length(2, 64);
            RuleFor(dto => dto.Content).Length(4, 2048);
        }
    }
}

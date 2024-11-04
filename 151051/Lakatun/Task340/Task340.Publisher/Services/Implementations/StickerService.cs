using AutoMapper;
using FluentValidation;
using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.DTO.ResponseDTO;
using Task340.Publisher.Exceptions;
using Task340.Publisher.Infrastructure.Validators;
using Task340.Publisher.Models;
using Task340.Publisher.Repositories.Interfaces;
using Task340.Publisher.Services.Interfaces;

namespace Task340.Publisher.Services.Implementations;

public class TagService(
    ITagRepository tagRepository,
    IMapper mapper,
    StickerRequestDtoValidator validator) : IStickerService
{
    private readonly IMapper _mapper = mapper;
    private readonly IStickerRepository _stickerRepository = stickerRepository;
    private readonly StickerRequestDtoValidator _validator = validator;

    public async Task<IEnumerable<StickerResponseDto>> GetStickersAsync()
    {
        var stickers = await _stickerRepository.GetAllAsync();
        return _mapper.Map<IEnumerable<StickerResponseDto>>(stickers);
    }

    public async Task<StickerResponseDto> GetStickerByIdAsync(long id)
    {
        var sticker = await _stickerRepository.GetByIdAsync(id)
                      ?? throw new NotFoundException(ErrorMessages.StickerNotFoundMessage(id));
        return _mapper.Map<StickerResponseDto>(sticker);
    }

    public async Task<StickerResponseDto> CreateStickerAsync(StickerRequestDto sticker)
    {
        _validator.ValidateAndThrow(sticker);
        var stickerToCreate = _mapper.Map<Sticker>(sticker);
        var createdSticker = await _stickerRepository.CreateAsync(stickerToCreate);
        return _mapper.Map<StickerResponseDto>(createdSticker);
    }

    public async Task<StickerResponseDto> UpdateStickerAsync(StickerRequestDto sticker)
    {
        _validator.ValidateAndThrow(sticker);
        var stickerToUpdate = _mapper.Map<Sticker>(sticker);
        var updatedSticker = await _stickerRepository.UpdateAsync(stickerToUpdate)
                             ?? throw new NotFoundException(ErrorMessages.StickerNotFoundMessage(sticker.Id));
        return _mapper.Map<StickerResponseDto>(updatedSticker);
    }

    public async Task DeleteStickerAsync(long id)
    {
        if (!await _stickerRepository.DeleteAsync(id))
            throw new NotFoundException(ErrorMessages.StickerNotFoundMessage(id));
    }
}
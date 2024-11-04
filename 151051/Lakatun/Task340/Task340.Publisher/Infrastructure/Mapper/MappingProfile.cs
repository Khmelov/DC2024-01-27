using AutoMapper;
using Task340.Publisher.DTO.RequestDTO;
using Task340.Publisher.DTO.ResponseDTO;
using Task340.Publisher.Models;

namespace Task340.Publisher.Infrastructure.Mapper;

public class MappingProfile : Profile
{
    public MappingProfile()
    {
        CreateMap<Creator, CreatorResponseDto>();
        CreateMap<CreatorRequestDto, Creator>();

        CreateMap<News, NewsResponseDto>();
        CreateMap<NewsRequestDto, News>();

        CreateMap<Sticker, StickerResponseDto>();
        CreateMap<StickerRequestDto, Sticker>();
    }
}
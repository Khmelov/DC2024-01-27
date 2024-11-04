using AutoMapper;
using Task350.Publisher.DTO.RequestDTO;
using Task350.Publisher.DTO.ResponseDTO;
using Task350.Publisher.Models;

namespace Task350.Publisher.Infrastructure.Mapper;

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
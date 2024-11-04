using AutoMapper;
using Task320.DTO.RequestDTO;
using Task320.DTO.ResponseDTO;
using Task320.Models;

namespace Task320.Infrastructure.Mapper
{
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

            CreateMap<Note, NoteResponseDto>();
            CreateMap<NoteRequestDto, Note>();
        }
    }
}

using AutoMapper;
using Task340.Discussion.DTO.RequestDTO;
using Task340.Discussion.DTO.ResponseDTO;
using Task340.Discussion.Models;

namespace Task340.Discussion.Infrastructure.Mapper
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            CreateMap<Note, NoteResponseDto>();
            CreateMap<NoteRequestDto, Note>();
        }
    }
}

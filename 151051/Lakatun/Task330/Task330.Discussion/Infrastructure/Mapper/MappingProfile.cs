using AutoMapper;
using Task330.Discussion.DTO.RequestDTO;
using Task330.Discussion.DTO.ResponseDTO;
using Task330.Discussion.Models;

namespace Task330.Discussion.Infrastructure.Mapper
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

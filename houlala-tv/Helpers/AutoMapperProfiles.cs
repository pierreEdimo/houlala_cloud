using AutoMapper;
using houlala_tv.Models;
using houlala_tv.Dto;

namespace houlala_tv.Helpers
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<Topic, TopicDto>().ReverseMap();

            CreateMap<CreateTopicDto, Topic>().ReverseMap()
                    .ForMember(x => x.ImageUrl, options => options.Ignore());
        }
    }
}
namespace houlala_tv.Dto
{
    public class CreateTopicDto
    {
        public String? label { get; set; }

        public IFormFile? ImageUrl { get; set; }
    }
}
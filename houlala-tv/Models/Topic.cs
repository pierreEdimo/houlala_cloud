namespace houlala_tv.Models
{
    public class Topic
    {
        public int Id { get; set; }

        public String? Label { get; set; }

        public virtual List<Course>? Courses { get; set; }

        public String? ImageUrl { get; set; }
    }
}
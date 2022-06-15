using System.ComponentModel.DataAnnotations;

namespace houlala_tv.Models
{
    public class Course
    {
        [Key]
        public int Id { get; set; }
        public String? Title { get; set; }
        public String? Description { get; set; }
        public int TopicId { get; set; }
        public virtual Topic? Topic { get; set; }
        public String? courseVideoUrl { get; set; }
        public DateTime? createdAt { get; set; } = DateTime.Now; 
    }
}
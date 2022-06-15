using Microsoft.EntityFrameworkCore;
using houlala_tv.Models;

namespace houlala_tv
{
    public class DatabaseContext : DbContext
    {
        public DatabaseContext(DbContextOptions<DatabaseContext> options) : base(options) { }

        public DbSet<Topic>? topics { get; set; }
        public DbSet<Course>? Courses { get; set; }

    }
}
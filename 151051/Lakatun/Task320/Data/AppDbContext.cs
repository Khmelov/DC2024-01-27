using Task320.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Hosting;

namespace Task320.Data
{
    public class AppDbContext : DbContext
    {
        public DbSet<Creator> Creators { get; set; }
        public DbSet<News> News { get; set; }
        public DbSet<Note> Notes { get; set; }
        public DbSet<Sticker> Stickers { get; set; }

        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<News>()
                .HasMany(n => n.Stickers)
                .WithMany(s => s.News)
                .UsingEntity<NewsSticker>(
                    ns => ns.HasOne<Sticker>().WithMany().HasForeignKey("StickerId"),
                    ns => ns.HasOne<News>().WithMany().HasForeignKey("NewsId"),
                    ns =>
                    {
                        ns.ToTable("tbl_News_Stickers");
                        ns.HasKey("NewsId", "StickerId");
                    });

            modelBuilder.Entity<Creator>()
                .HasIndex(c => c.Login)
                .IsUnique();

            modelBuilder.Entity<News>()
                .HasIndex(c => c.Title)
                .IsUnique();

            modelBuilder.Entity<Sticker>()
                .HasIndex(c => c.Name)
                .IsUnique();
        }


    }
}
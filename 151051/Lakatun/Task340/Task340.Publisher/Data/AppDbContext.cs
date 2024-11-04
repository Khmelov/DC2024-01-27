using Task340.Publisher.Models;
using Microsoft.EntityFrameworkCore;

namespace Lab4.Publisher.Data;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
    {
    }

    public DbSet<Creator> Creators { get; set; }
    public DbSet<News> News { get; set; }
    public DbSet<Sticker> Stickers { get; set; }

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
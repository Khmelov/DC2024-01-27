using Task340.Publisher.Data;
using Task340.Publisher.Models;
using Task340.Publisher.Repositories.Interfaces;

namespace Task340.Publisher.Repositories.Implementations;

public class NewsSqlRepository(AppDbContext context) : BaseSqlRepository<News>(context), INewsRepository
{
}
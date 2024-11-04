using Task320.Data;
using Task320.Models;
using Task320.Repositories.Interfaces;

namespace Task320.Repositories.Implementations
{
    public class NewsSqlRepository(AppDbContext context) : BaseSqlRepository<News>(context), INewsRepository
    {

    }
}

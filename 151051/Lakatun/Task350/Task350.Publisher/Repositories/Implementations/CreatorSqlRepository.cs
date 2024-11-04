using Task350.Publisher.Data;
using Task350.Publisher.Models;
using Task350.Publisher.Repositories.Interfaces;

namespace Task350.Publisher.Repositories.Implementations;

public class CreatorSqlRepository(AppDbContext context) : BaseSqlRepository<Creator>(context), ICreatorRepository
{
}
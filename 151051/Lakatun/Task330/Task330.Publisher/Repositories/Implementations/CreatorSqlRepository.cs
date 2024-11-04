using Task330.Publisher.Data;
using Task330.Publisher.Models;
using Task330.Publisher.Repositories.Interfaces;

namespace Task330.Publisher.Repositories.Implementations;

public class CreatorSqlRepository(AppDbContext context) : BaseSqlRepository<Creator>(context), ICreatorRepository
{
}
using Task340.Publisher.Data;
using Task340.Publisher.Models;
using Task340.Publisher.Repositories.Interfaces;

namespace Task340.Publisher.Repositories.Implementations;

public class CreatorSqlRepository(AppDbContext context) : BaseSqlRepository<Creator>(context), ICreatorRepository
{
}
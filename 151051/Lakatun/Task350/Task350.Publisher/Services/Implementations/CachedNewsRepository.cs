using Task350.Publisher.Repositories.Interfaces;
using Task350.Publisher.Models;

namespace Task350.Publisher.Services.Implementations;

public class CachedNewsRepository : INewsRepository
{
    public Task<List<News>> GetAllAsync()
    {
        throw new NotImplementedException();
    }

    public IQueryable<News> GetAllWithFilteringAsync()
    {
        throw new NotImplementedException();
    }

    public Task<News?> GetByIdAsync(long id)
    {
        throw new NotImplementedException();
    }

    public Task<News> CreateAsync(News creatorModel)
    {
        throw new NotImplementedException();
    }

    public Task<News?> UpdateAsync(long id, News updatedNews)
    {
        throw new NotImplementedException();
    }

    public Task<News?> DeleteAsync(long id)
    {
        throw new NotImplementedException();
    }

    Task<IEnumerable<News>> IBaseRepository<News>.GetAllAsync()
    {
        throw new NotImplementedException();
    }

    public Task<News?> UpdateAsync(News entity)
    {
        throw new NotImplementedException();
    }

    Task<bool> IBaseRepository<News>.DeleteAsync(long id)
    {
        throw new NotImplementedException();
    }
}
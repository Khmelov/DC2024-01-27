namespace Task330.Discussion.Repositories.Interfaces
{
    public interface ICassandraRepository<T> where T : class
    {
        Task<IEnumerable<T>> GetAllAsync();

        Task<T?> GetByIdAsync(long id);

        Task<T> CreateAsync(T entity);

        Task<T?> UpdateAsync(T entity);

        Task<bool> DeleteAsync(long id);
    }
}

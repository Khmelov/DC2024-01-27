namespace Task320.Repositories.Interfaces
{
    public interface IBaseRepository<T> where T : class
    {
        Task<IEnumerable<T>> GetAllAsync();

        Task<T?> GetByIdAsync(long id);

        Task<T> CreateAsync(T entity);

        Task<T?> UpdateAsync(T entity);

        Task<bool> DeleteAsync(long id);
    }
}

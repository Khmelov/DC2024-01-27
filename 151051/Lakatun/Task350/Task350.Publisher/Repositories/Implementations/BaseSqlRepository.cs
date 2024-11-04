using Task350.Publisher.Data;
using Task350.Publisher.Models;
using Task350.Publisher.Repositories.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace Task350.Publisher.Repositories.Implementations;

public abstract class BaseSqlRepository<DbModel> : IBaseRepository<DbModel>
    where DbModel : BaseModel

{
    protected readonly AppDbContext _context;
    protected readonly DbSet<DbModel> _dbSet;

    public BaseSqlRepository(AppDbContext context)
    {
        _context = context;
        _dbSet = context.Set<DbModel>();
    }

    public virtual async Task<DbModel?> GetByIdAsync(long id)
    {
        return await _dbSet.FirstOrDefaultAsync(x => x.Id == id);
    }

    public virtual async Task<IEnumerable<DbModel>> GetAllAsync()
    {
        return await _dbSet.ToListAsync();
    }

    public virtual async Task<DbModel> CreateAsync(DbModel model)
    {
        var newModel = await _dbSet.AddAsync(model);
        await _context.SaveChangesAsync();
        return newModel.Entity;
    }

    public virtual async Task<DbModel?> UpdateAsync(DbModel model)
    {
        var newModel = _context.Update(model);
        await _context.SaveChangesAsync();
        return newModel.Entity;
    }

    public virtual async Task<bool> DeleteAsync(long id)
    {
        var model = await GetByIdAsync(id);
        if (model is null) return false;
        _dbSet.Remove(model);
        await _context.SaveChangesAsync();
        return true;
    }
}
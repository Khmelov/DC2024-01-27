using Cassandra;
using ISession = Cassandra.ISession;
using Task340.Discussion.Repositories.Interfaces;
using Task340.Discussion.Models;

namespace Task340.Discussion.Repositories.Implementations;

public class CassandraNoteRepository(ISession session) : ICassandraRepository<Note>
{
    public async Task<IEnumerable<Note>> GetAllAsync()
    {
        var query = "SELECT * FROM tbl_Note";
        var result = await session.ExecuteAsync(new SimpleStatement(query));
        var all = result.Select(MapNoteFromRow).ToList();
        return all;
    }

    public async Task<Note?> GetByIdAsync(long id)
    {
        var query = "SELECT * FROM tbl_Note WHERE id = ?";
        var result = await session.ExecuteAsync(new SimpleStatement(query, id));
        var row = result.FirstOrDefault();
        return row != null ? MapNoteFromRow(row) : null;
    }

    public async Task<Note> CreateAsync(Note entity)
    {
        var query = $"INSERT INTO tbl_Note (id, news_id, content) VALUES (?, ?, ?)";
        var statement = await session.PrepareAsync(query);
        var boundStatement = statement.Bind(entity.Id, entity.NewsId, entity.Content);
        await session.ExecuteAsync(boundStatement);
        return entity;
    }

    public async Task<Note?> UpdateAsync(Note entity)
    {
        var existingNote = await GetByIdAsync(entity.Id);
        if (existingNote == null)
            return null;

        var query = $"UPDATE tbl_Note SET news_id = ?, content = ? WHERE id = ?";
        var statement = session.Prepare(query);
        var boundStatement = statement.Bind(entity.NewsId, entity.Content, entity.Id);
        await session.ExecuteAsync(boundStatement);
        return entity;
    }

    public async Task<bool> DeleteAsync(long id)
    {
        var existingNote = await GetByIdAsync(id);
        if (existingNote == null)
            return false;

        var query = $"DELETE FROM tbl_Note WHERE id = ?";
        var statement = session.Prepare(query);
        var boundStatement = statement.Bind(id);
        await session.ExecuteAsync(boundStatement);
        return true;
    }

    private Note MapNoteFromRow(Row row)
    {
        return new Note
        {
            Id = row.GetValue<long>("id"),
            NewsId = row.GetValue<long>("news_id"),
            Content = row.GetValue<string>("content"),
        };
    }
}
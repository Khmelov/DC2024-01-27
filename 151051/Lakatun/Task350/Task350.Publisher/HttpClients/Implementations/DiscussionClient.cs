using System.Text;
using System.Text.Json;
using Task350.Publisher.DTO.RequestDTO;
using Task350.Publisher.DTO.ResponseDTO;
using Task350.Publisher.HttpClients.Interfaces;

namespace Task350.Publisher.HttpClients.Implementations;

public class DiscussionClient(IHttpClientFactory factory) : IDiscussionClient
{
    private static readonly JsonSerializerOptions JsonSerializerOptions = new()
    {
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase
    };

    public async Task<IEnumerable<NoteResponseDto>> GetNotesAsync()
    {
        var httpClient = factory.CreateClient(nameof(DiscussionClient));
        var response = await httpClient.GetAsync(new Uri("notes", UriKind.Relative));
        response.EnsureSuccessStatusCode();

        var responseJson = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<IEnumerable<NoteResponseDto>>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
    }

    public async Task<NoteResponseDto> GetNoteByIdAsync(long id)
    {
        var httpClient = factory.CreateClient(nameof(DiscussionClient));
        var response = await httpClient.GetAsync(new Uri($"notes/{id}", UriKind.Relative));
        response.EnsureSuccessStatusCode();

        var responseJson = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<NoteResponseDto>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
    }

    public async Task<NoteResponseDto> CreateNoteAsync(NoteRequestDto post)
    {
        var httpClient = factory.CreateClient(nameof(DiscussionClient));

        var postJson = JsonSerializer.Serialize(post);
        var content = new StringContent(postJson, Encoding.UTF8, "application/json");

        var response = await httpClient.PostAsync("notes", content);
        response.EnsureSuccessStatusCode();

        var responseJson = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<NoteResponseDto>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
    }

    public async Task<NoteResponseDto> UpdateNoteAsync(NoteRequestDto post)
    {
        var httpClient = factory.CreateClient(nameof(DiscussionClient));
        var postJson = JsonSerializer.Serialize(post);
        var content = new StringContent(postJson, Encoding.UTF8, "application/json");

        var response = await httpClient.PutAsync("notes", content);
        response.EnsureSuccessStatusCode();

        var responseJson = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<NoteResponseDto>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
    }

    public async Task DeleteNoteAsync(long id)
    {
        var httpClient = factory.CreateClient(nameof(DiscussionClient));
        var response = await httpClient.DeleteAsync($"notes/{id}");
        response.EnsureSuccessStatusCode();
    }
}
namespace Task340.Publisher.Exceptions;

public static class ErrorMessages
{
    public static string CreatorNotFoundMessage(long id)
    {
        return $"Creator with id {id} was not found.";
    }

    public static string NewsNotFoundMessage(long id)
    {
        return $"News with id {id} was not found.";
    }

    public static string StickerNotFoundMessage(long id)
    {
        return $"Sticker with id {id} was not found.";
    }

    public static string NoteNotFoundMessage(long id)
    {
        return $"Note with id {id} was not found.";
    }
}
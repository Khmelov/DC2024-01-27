namespace Task340.Messaging.MessageBus;

public interface IMessageBus<Tk, Tv>
{
    Task PublishAsync(Tk key, Tv message);
}
namespace Task340.Messaging.Consumer;

public interface IKafkaHandler<Tk, Tv> 
{
    Task HandleAsync(Tk key, Tv value);
}
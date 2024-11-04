using System.Text;
using Confluent.Kafka;
using Newtonsoft.Json;

namespace Task340.Messaging.KafkaSerialization;

internal sealed class Serializer<T> : ISerializer<T>
{
    public byte[] Serialize(T data, SerializationContext context)
    {
        if (typeof(T) == typeof(Null))
            return null!;

        if (typeof(T) == typeof(Ignore))
            throw new NotSupportedException("Not Supported.");

        var json = JsonConvert.SerializeObject(data);

        return Encoding.UTF8.GetBytes(json);
    }
}
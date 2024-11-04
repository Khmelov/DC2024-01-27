using System.Text;
using Confluent.Kafka;
using Newtonsoft.Json;

namespace Task340.Messaging.KafkaSerialization;

internal sealed class Deserializer<T> : IDeserializer<T>
{
    public T Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
    {
        if (typeof(T) == typeof(Null))
        {
            if (data.Length > 0)
                throw new ArgumentException("The data is null not null.");
            return default;
        }

        if (typeof(T) == typeof(Ignore))
            return default;

        var dataJson = Encoding.UTF8.GetString(data);

        return JsonConvert.DeserializeObject<T>(dataJson);
    }
}
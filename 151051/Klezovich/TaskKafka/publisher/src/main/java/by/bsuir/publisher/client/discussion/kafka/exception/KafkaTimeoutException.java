package by.bsuir.publisher.client.discussion.kafka.exception;

public class KafkaTimeoutException extends RuntimeException {

    public KafkaTimeoutException() {
        super("Timeout while trying to get response");
    }

    public KafkaTimeoutException(Throwable cause) {
        super(cause);
    }

    public KafkaTimeoutException(String name) {
        super("Timeout while trying to get response '" + name + "'");
    }

    public KafkaTimeoutException(String name, Throwable cause) {
        super("Timeout while trying to get response '" + name + "': " + cause);
    }
}

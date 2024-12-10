package by.bsuir.publisher.client.discussion.kafka.exception;

public class KafkaResponseException extends RuntimeException {

    public KafkaResponseException() {
        super("Unexpected error occurred while trying to get response.");
    }

    public KafkaResponseException(Throwable cause) {
        super(cause);
    }

    public KafkaResponseException(String message) {
        super("Unexpected error occurred while trying to get response: " + message);
    }
}

package by.bsuir.publisherservice.client.discussionservice.kafka.exception;

public class DiscussionServiceTimeoutException extends RuntimeException {
    public DiscussionServiceTimeoutException(String message) {
        super(message);
    }
}

package by.bsuir.publisherservice.exception;

public class DiscussionServiceIncorrectRequestException extends RuntimeException {
    public DiscussionServiceIncorrectRequestException() {
        super("Incorrect request to discussion service");
    }

    public DiscussionServiceIncorrectRequestException(String message) {
        super(message);
    }
}

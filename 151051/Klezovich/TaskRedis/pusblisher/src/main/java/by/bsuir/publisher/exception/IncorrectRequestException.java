package by.bsuir.publisher.exception;

public class IncorrectRequestException extends RuntimeException {

    public IncorrectRequestException() {
        super("Incorrect request to the discussion microservice.");
    }


    public IncorrectRequestException(Throwable cause) {
        super(cause);
    }

    public IncorrectRequestException(String message) {
        super(message);
    }

    public IncorrectRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

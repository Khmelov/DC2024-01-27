package by.bsuir.taskjpa.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String name, Long id) {
        super("Error occurred while search entity '" + name + "' with ID '" + id + "'");
    }

    public EntityNotFoundException(String name, Long id, Throwable cause) {
        super("Error occurred while search entity '" + name + "' with ID '" + id + "': " + cause);
    }
}

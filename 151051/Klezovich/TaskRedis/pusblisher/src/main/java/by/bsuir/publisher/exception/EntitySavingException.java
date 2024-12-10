package by.bsuir.publisher.exception;

public class EntitySavingException extends RuntimeException {

    public EntitySavingException() {
        super();
    }

    public EntitySavingException(String message) {
        super(message);
    }

    public EntitySavingException(Throwable cause) {
        super(cause);
    }

    public EntitySavingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntitySavingException(String name, Long id) {
        super("Error occurred while saving entity '" + name + "' with ID '" + id + "'");
    }

    public EntitySavingException(String name, Long id, Throwable cause) {
        super("Error occurred while saving entity '" + name + "' with ID '" + id + "': " + cause);
    }
}

package by.bsuir.discussionservice.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long entityId) {
        super("Unable to find " + entityName + " with id " + entityId);
    }
}

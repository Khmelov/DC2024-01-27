package by.bsuir.publisherservice.exception;

public class EntityNotSavedException extends RuntimeException{
    public EntityNotSavedException(String entityName, Long entityId) {
        super("Unable to save " + entityName + " with id " + entityId);
    }
}

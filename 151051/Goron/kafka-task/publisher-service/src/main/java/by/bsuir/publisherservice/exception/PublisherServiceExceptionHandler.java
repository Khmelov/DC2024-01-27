package by.bsuir.publisherservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import by.bsuir.publisherservice.client.discussionservice.kafka.exception.DiscussionServiceResponseException;
import by.bsuir.publisherservice.client.discussionservice.kafka.exception.DiscussionServiceTimeoutException;
import by.bsuir.publisherservice.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class PublisherServiceExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, 0);
    }

    @ExceptionHandler(EntityNotSavedException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotSavedException(EntityNotSavedException e) {
        return buildErrorResponse(e, HttpStatus.UNPROCESSABLE_ENTITY, 0);
    }

    @ExceptionHandler(DiscussionServiceTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleDiscussionServiceTimeoutException(DiscussionServiceTimeoutException e) {
        return buildErrorResponse(e, HttpStatus.GATEWAY_TIMEOUT, 0);
    }
    
    @ExceptionHandler(DiscussionServiceResponseException.class)
    public ResponseEntity<ErrorResponse> handleDiscussionServiceResponseException(DiscussionServiceResponseException e) {
        return buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, 0);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, 0);
    }

    @ExceptionHandler(DiscussionServiceIncorrectRequestException.class)
    public ResponseEntity<ErrorResponse> handleDiscussionServiceIncorrectRequestException(
            DiscussionServiceIncorrectRequestException e
    ) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, 1);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return buildErrorResponse(e, HttpStatus.FORBIDDEN, 0);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpStatus httpStatus, Integer distcompTaskCode) {
        return ResponseEntity.status(httpStatus)
                             .body(ErrorResponse.builder()
                                                .errorMessage(e.getMessage())
                                                .httpStatusCode(httpStatus.value())
                                                .restTaskCode(distcompTaskCode)
                                                .build());
    }
}

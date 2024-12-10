package by.bsuir.taskjpa.exception;

import by.bsuir.taskjpa.model.dto.response.ErrorResponseTo;
import by.bsuir.taskjpa.util.ErrorsHandlerUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseTo> handleEntityNotFoundException(EntityNotFoundException e) {
        return ErrorsHandlerUtil.getErrorResponseEntity(HttpStatus.NOT_FOUND, 1, e.getMessage());
    }

    @ExceptionHandler(EntitySavingException.class)
    public ResponseEntity<ErrorResponseTo> handleEntitySavingException(EntitySavingException e) {
        return ErrorsHandlerUtil.getErrorResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, 1, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseTo> handleConstraintViolationException(
            ConstraintViolationException e) {
        return ErrorsHandlerUtil.getErrorResponseEntity(HttpStatus.BAD_REQUEST, 3, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseTo> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ErrorsHandlerUtil.getErrorResponseEntity(HttpStatus.FORBIDDEN, 3, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseTo> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getFieldErrors()
                .stream()
                .map(e ->  e.getField() + " : " + e.getDefaultMessage())
                .toList();
        return ErrorsHandlerUtil.getErrorResponseEntity(HttpStatus.BAD_REQUEST, 3,
                "Argument validation error: " + errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseTo> handleRuntimeException(){
        return ErrorsHandlerUtil.getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, 1,
                "Internal server error. Try later again");
    }
}

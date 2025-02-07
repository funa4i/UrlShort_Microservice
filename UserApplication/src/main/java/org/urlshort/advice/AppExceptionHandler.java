package org.urlshort.advice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.urlshort.advice.enums.ExceptionAnswer;
import org.urlshort.exceptions.AlreadyExistsException;
import org.urlshort.exceptions.NullObjectException;


@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({NullObjectException.class})
    public ResponseEntity<ExceptionView> notExistHandler(Exception ex){
        log.warn(ex.getMessage());
        ExceptionView exc = new ExceptionView(ExceptionAnswer.REQUIRED_OBJECT_NOT_FOUND);
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(exc);
    }

    @ExceptionHandler({ConstraintViolationException.class, AlreadyExistsException.class})
    public ResponseEntity<ExceptionView> badValidation(Exception ex){
        log.warn(ex.getMessage());
        ExceptionView exc = null;
        if (ex instanceof AlreadyExistsException){
            exc = new ExceptionView(ExceptionAnswer.OBJECT_ALREADY_EXISTS);
        }
        else if (ex instanceof ConstraintViolationException){
            exc = new ExceptionView(ExceptionAnswer.INVALID_DATA_FORMAT);
        }
        assert exc != null;
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(exc);
    }
}

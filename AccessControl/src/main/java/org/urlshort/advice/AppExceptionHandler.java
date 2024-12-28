package org.urlshort.advice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.urlshort.advice.enums.ExceptionAnswer;
import org.urlshort.exceptions.NullObjectException;


@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, NullObjectException.class})
    public  ResponseEntity<ExceptionView> notExistHandler(Exception ex){
        ExceptionView exc = null;
        log.warn(ex.getMessage());
        if (ex instanceof ConstraintViolationException){
            exc = new ExceptionView(ExceptionAnswer.INVALID_DATA_FORMAT);
        }
        if (ex instanceof NullObjectException){
            exc = new ExceptionView(ExceptionAnswer.REQUIRED_OBJECT_NOT_FOUND);
        }
        assert exc != null;
        exc.setMessage(ex.getMessage());

        return ResponseEntity
                .status(404)
                .body(exc);
    }
}

package org.urlshort.advice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.urlshort.advice.enums.ExceptionAnswer;
import org.urlshort.domain.exceptions.AttemptCountException;
import org.urlshort.domain.exceptions.NotFoundException;
import org.urlshort.domain.exceptions.UncheckedException;


@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({NullPointerException.class, NotFoundException.class, })
    public ResponseEntity<ExceptionView> notExistHandler(Exception ex){
        ExceptionView exc = new ExceptionView(ExceptionAnswer.REQUIRED_OBJECT_NOT_FOUND);
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(exc);
    }

    @ExceptionHandler({ConstraintViolationException.class,
            AttemptCountException.class, UncheckedException.class, })
    public ResponseEntity<ExceptionView> badValidation(Exception ex){
        ExceptionView exc = new ExceptionView(ExceptionAnswer.INVALID_DATA_FORMAT);
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(exc);
    }
}

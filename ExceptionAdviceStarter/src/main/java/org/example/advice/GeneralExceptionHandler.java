package org.example.advice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.example.advice.enums.ExceptionAnswer;
import org.example.advice.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({NullObjectException.class, NotFoundException.class,})
    public ResponseEntity<ExceptionView> notExistHandler(Exception ex){
        log.warn(ex.getMessage());
        ExceptionView exc = new ExceptionView(ExceptionAnswer.REQUIRED_OBJECT_NOT_FOUND);
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(exc);
    }

    @ExceptionHandler({ConstraintViolationException.class, UncheckedException.class})
    public ResponseEntity<ExceptionView> badValidation(Exception ex){
        log.warn(ex.getMessage());
        ExceptionView exc = new ExceptionView(ExceptionAnswer.INVALID_DATA_FORMAT);
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(exc);
    }
    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<ExceptionView> alreadyExists(Exception ex){
        log.warn(ex.getMessage());
        ExceptionView exc = new ExceptionView(ExceptionAnswer.OBJECT_ALREADY_EXISTS);
        exc.setMessage(ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(exc);
    }
}

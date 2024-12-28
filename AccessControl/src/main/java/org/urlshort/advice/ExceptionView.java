package org.urlshort.advice;

import lombok.Data;
import org.urlshort.advice.enums.ExceptionAnswer;

import java.time.LocalDateTime;

@Data
public class ExceptionView {


    private ExceptionAnswer exception;

    private LocalDateTime timestamp;

    private String message;

    public ExceptionView(ExceptionAnswer exception) {
        this.exception = exception;
        this.timestamp = LocalDateTime.now();
    }
}

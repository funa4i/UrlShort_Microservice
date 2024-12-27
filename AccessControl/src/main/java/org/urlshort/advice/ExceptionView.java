package org.urlshort.advice;

import lombok.Data;
import org.urlshort.domain.enums.ExceptionAnswer;

import java.time.LocalDateTime;

@Data
public class ExceptionView {


    private ExceptionAnswer exception;

    private LocalDateTime timestamp;

    public ExceptionView(ExceptionAnswer exception) {
        this.exception = exception;
        this.timestamp = LocalDateTime.now();
    }
}

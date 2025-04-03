package org.urlshort.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.urlshort.enums.ExceptionAnswer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionView {
    private ExceptionAnswer exception;
    private LocalDateTime timestamp;
    private String message;

    public ExceptionView(ExceptionAnswer exception) {
        this.exception = exception;
        this.timestamp = LocalDateTime.now();
    }
}

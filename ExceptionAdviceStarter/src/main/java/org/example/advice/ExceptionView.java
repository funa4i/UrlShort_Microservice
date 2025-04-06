package org.example.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.advice.enums.ExceptionAnswer;

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

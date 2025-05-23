package org.example.advice.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(Class<?> clas, String object) {
        super("Object " + clas.getSimpleName() + " " + object + "already exists");
    }
}

package org.urlshort.exceptions;

public class InvalidAuthorizeException extends RuntimeException {
    public InvalidAuthorizeException() {
        super("Login or password is incorrect");
    }
}

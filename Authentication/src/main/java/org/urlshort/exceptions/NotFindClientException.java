package org.urlshort.exceptions;

public class NotFindClientException extends RuntimeException {
    public NotFindClientException(String message) {
        super("Something wasn't found: " + message);
    }
}

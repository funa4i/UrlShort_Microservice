package org.urlshort.exceptions;

public class AlreadyExistsException extends RuntimeException {
  public AlreadyExistsException(Class<?> cl, String obj) {
    super(cl.getSimpleName() + " " + obj + " " + " already exists");
  }
}

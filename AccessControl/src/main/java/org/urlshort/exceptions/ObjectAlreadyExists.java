package org.urlshort.exceptions;

public class ObjectAlreadyExists extends RuntimeException {
  public ObjectAlreadyExists(Class<?> cl,  String obj) {
    super(cl.getSimpleName() + " " + obj + " " + " already exists");
  }
}

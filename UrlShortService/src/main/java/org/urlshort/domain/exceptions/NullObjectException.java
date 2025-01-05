package org.urlshort.domain.exceptions;


public class NullObjectException extends RuntimeException{

    public NullObjectException(Class<?> obj, String param){
        super("Object" + " " + obj.getSimpleName() + " doesn't found by " + param);
    }
}

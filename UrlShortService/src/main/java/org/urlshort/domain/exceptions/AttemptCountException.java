package org.urlshort.domain.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class AttemptCountException extends RuntimeException{

    public AttemptCountException(){
        super("The link creation limit has been reached.");
    }

    public AttemptCountException(String message){
        super(message);
    }
}

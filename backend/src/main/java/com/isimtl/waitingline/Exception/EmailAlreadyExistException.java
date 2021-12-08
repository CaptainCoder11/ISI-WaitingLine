package com.isimtl.waitingline.Exception;

public class EmailAlreadyExistException extends RuntimeException{

    public EmailAlreadyExistException(String message,Throwable cause) {
        super(message,cause);
    }

    public EmailAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}

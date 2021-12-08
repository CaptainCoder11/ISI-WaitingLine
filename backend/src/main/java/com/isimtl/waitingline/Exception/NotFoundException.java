package com.isimtl.waitingline.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message,Throwable cause) {
        super(message,cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}

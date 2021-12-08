package com.isimtl.waitingline.Exception;

public class PhoneAlreadyExistException extends RuntimeException{

    public PhoneAlreadyExistException(String message, Throwable cause) {
        super(message,cause);
    }

    public PhoneAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public PhoneAlreadyExistException(String message) {
        super(message);
    }
}

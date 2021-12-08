package com.isimtl.waitingline.Exception;

public class OtpException extends RuntimeException {
    public OtpException(String message, Throwable cause) {
        super(message, cause);
    }

    public OtpException(Throwable cause) {
        super(cause);
    }

    public OtpException(String message) {
        super(message);
    }

}

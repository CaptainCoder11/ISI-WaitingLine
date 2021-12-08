package com.isimtl.waitingline.entity;

import java.util.Arrays;

public class ErrorResponse {
    private int status;
    private String message;
    private String stackTrace;
    private String error;

    public ErrorResponse(int status, String message, Exception exception) {
        this.status = status;
        this.message = message;
        this.stackTrace = Arrays.toString(exception.getStackTrace());
        this.error = message;
    }

    public String getError() {
        return message;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

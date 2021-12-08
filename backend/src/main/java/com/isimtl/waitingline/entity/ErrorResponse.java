package com.isimtl.waitingline.entity;

public class ErrorResponse {
    private int status;
    private String message;
    private Exception exception;

    public ErrorResponse(int status, String message, Exception exception) {
        this.status = status;
        this.message = message;
        this.exception = exception;
    }

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
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

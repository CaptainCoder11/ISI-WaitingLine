package com.isimtl.waitingline.ExceptionHadler;

import com.isimtl.waitingline.Exception.EmailAlreadyExistException;
import com.isimtl.waitingline.Exception.NotFoundException;
import com.isimtl.waitingline.Exception.PhoneAlreadyExistException;
import com.isimtl.waitingline.entity.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @Value("${PROFILE}")
    private String PROFILE;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc)
    {

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),exc);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException exc)
    {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),exc);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EmailAlreadyExistException exc)
    {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),exc);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(PhoneAlreadyExistException exc)
    {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),exc);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }



}

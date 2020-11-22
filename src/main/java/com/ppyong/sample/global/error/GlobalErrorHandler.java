package com.ppyong.sample.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRNE(ResourceNotFoundException e){
        Long resourceId = e.getResourceId();
        return new ResponseEntity<>(new ErrorResponse("", ""), HttpStatus.NOT_FOUND);
    }
}

package com.controller.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String > handleItemNotFoundException(ItemNotFoundException itemNotFoundException){

        return new ResponseEntity<>(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidRatingException.class)
    public ResponseEntity<String> handleInvalidRatingException(InvalidRatingException invalidRatingException){
        return new ResponseEntity<>(invalidRatingException.getMessage(),HttpStatus.NOT_FOUND);
    }
}

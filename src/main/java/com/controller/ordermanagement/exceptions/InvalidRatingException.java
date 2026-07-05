package com.controller.ordermanagement.exceptions;

public class InvalidRatingException extends RuntimeException{

    public InvalidRatingException(String message){
        super(message);
    }
}

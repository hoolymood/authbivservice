package com.example.authbivservice.handler.exception;

public class ConflictCodeException extends RuntimeException{

    public ConflictCodeException(String message){
        super(message);
    }
}

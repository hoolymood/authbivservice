package com.example.authbivservice.handler.exception;

public class DuplicateCodeException extends RuntimeException{

    public DuplicateCodeException (String message){
        super(message);
    }
}

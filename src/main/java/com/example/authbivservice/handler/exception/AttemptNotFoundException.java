package com.example.authbivservice.handler.exception;

public class AttemptNotFoundException extends RuntimeException {

    public AttemptNotFoundException(String message) {
        super(message);
    }
}

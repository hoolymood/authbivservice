package com.example.authbivservice.handler.exception;

public class AttemptLimitException extends RuntimeException {

    public AttemptLimitException(String message) {
        super(message);
    }
}

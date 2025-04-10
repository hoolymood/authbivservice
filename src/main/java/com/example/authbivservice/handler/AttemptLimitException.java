package com.example.authbivservice.handler;

public class AttemptLimitException extends RuntimeException {

    public AttemptLimitException(String message) {
        super(message);
    }
}

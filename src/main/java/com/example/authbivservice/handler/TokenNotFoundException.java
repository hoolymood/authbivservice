package com.example.authbivservice.handler;

public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(String message) {
        super(message);
    }
}

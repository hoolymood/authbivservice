package com.example.authbivservice.service.impl;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String generate() {
        int randomCode = (int) ((Math.random() * (999999 - 100000)) + 100000);
        return String.valueOf(randomCode);
    }
}

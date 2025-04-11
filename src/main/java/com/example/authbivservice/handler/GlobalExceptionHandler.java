package com.example.authbivservice.handler;

import com.example.authbivservice.handler.exception.AttemptLimitException;
import com.example.authbivservice.handler.exception.AttemptNotFoundException;
import com.example.authbivservice.handler.exception.TokenNotFoundException;
import com.example.authbivservice.handler.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> tokenNotFoundException(TokenNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> attemptNotFoundException(AttemptNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> attemptLimitException(AttemptLimitException e) {

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(e.getMessage());
    }
}

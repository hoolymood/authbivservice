package com.example.authbivservice.domen.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthEmailDto(
        @NotNull
        @Email
        String email
) {
}

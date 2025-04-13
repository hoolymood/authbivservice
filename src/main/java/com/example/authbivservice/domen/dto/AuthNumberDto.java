package com.example.authbivservice.domen.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.example.authbivservice.utils.Constants.REGEX_PHONE_NUMBER;

public record AuthNumberDto(
        @NotNull
        @Pattern(regexp = REGEX_PHONE_NUMBER)
        String number
) {
}

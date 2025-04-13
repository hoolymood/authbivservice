package com.example.authbivservice.domen.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.example.authbivservice.utils.Constants.REGEX_CODE;

public record TokenDto(
        @NotNull
        @NotEmpty
        @Pattern(regexp = REGEX_CODE)
        String code
) {
}

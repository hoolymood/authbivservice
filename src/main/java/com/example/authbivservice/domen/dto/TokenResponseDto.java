package com.example.authbivservice.domen.dto;

import com.example.authbivservice.domen.Status;

public record TokenResponseDto(
        String code,
        Status status
) {
}

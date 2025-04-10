package com.example.authbivservice.domen.dto;

import com.example.authbivservice.domen.TypeAuth;

public record AuthDto(
        String email,
        String number,
        TypeAuth typeAuth
) {
}

package com.example.authbivservice.domen.dto;

import com.example.authbivservice.domen.Status;

import java.time.LocalDateTime;

public record ResponseStatusDto(
        Status status,
        LocalDateTime createdAt
) {
}

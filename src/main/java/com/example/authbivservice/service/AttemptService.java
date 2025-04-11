package com.example.authbivservice.service;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.ResponseStatusDto;
import com.example.authbivservice.domen.entity.Attempt;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AttemptService {

    long countByAfter(LocalDateTime since, UUID tokenId);
    Attempt save(Attempt attempt);
    ResponseStatusDto lastTry(String code, Status status);
}

package com.example.authbivservice.service;

import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.domen.entity.Token;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AttemptService {

    long countByAfter (LocalDateTime since, UUID tokenId);
    Attempt save (Attempt attempt);
}

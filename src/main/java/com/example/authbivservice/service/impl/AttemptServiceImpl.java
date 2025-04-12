package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.ResponseStatusDto;
import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.handler.exception.TokenNotFoundException;
import com.example.authbivservice.log.Loggable;
import com.example.authbivservice.repo.AttemptRepo;
import com.example.authbivservice.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Loggable
public class AttemptServiceImpl implements AttemptService {

    private final AttemptRepo attemptRepo;

    @Override
    public long countByAfter(LocalDateTime after, UUID tokenId) {
        return attemptRepo
                .countByAuditCreatedAtAfterAndByToken(after, tokenId);
    }

    @Override
    public Attempt save(Attempt attempt) {
        return attemptRepo.save(attempt);
    }

    @Override
    public ResponseStatusDto lastTry(String code, Status status) {
        Attempt attempt = attemptRepo.findFirstByTokenCodeAndStatusOrderByCreatedAtDesc(code, status)
                .orElseThrow(
                        () -> new TokenNotFoundException(
                                String.format("Attempt for %s not exist", code)
                        ));

        return new ResponseStatusDto(attempt.getStatus(), attempt.getCreatedAt());
    }
}

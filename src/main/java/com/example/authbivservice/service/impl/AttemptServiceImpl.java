package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.repo.AttemptRepo;
import com.example.authbivservice.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
}

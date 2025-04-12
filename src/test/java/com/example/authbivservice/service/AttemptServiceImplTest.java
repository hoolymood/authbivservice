package com.example.authbivservice.service;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.ResponseStatusDto;
import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.handler.exception.AttemptNotFoundException;
import com.example.authbivservice.repo.AttemptRepo;
import com.example.authbivservice.service.impl.AttemptServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttemptServiceImplTest {

    @Mock
    AttemptRepo attemptRepo;

    @InjectMocks
    AttemptServiceImpl attemptService;

    @AfterEach
    void verifyMockInteractions() {
        verifyNoMoreInteractions(attemptRepo);
    }

    @Test
    @DisplayName("возвращает кол-во attempt")
    void countByAfter() {
        //given
        long expected = 3;
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID tokenId = UUID.randomUUID();
        when(attemptRepo.countByAuditCreatedAtAfterAndByToken(localDateTime, tokenId)).thenReturn(expected);

        //when
        long actual = attemptService.countByAfter(localDateTime, tokenId);

        //then
        Assertions.assertEquals(expected, actual);

    }

    @Test
    @DisplayName("сохраняет Attempt")
    void save() {

        //given
        Attempt attempt = new Attempt(UUID.randomUUID(), new Token(), Status.SUCCESS);
        when(attemptRepo.save(attempt)).thenReturn(attempt);

        //when
        Attempt actual = attemptService.save(attempt);

        //then
        Assertions.assertEquals(attempt, actual);
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"SUCCESS", "FAILED"})
    @DisplayName("возвращает ResponseStatusDto")
    void lastTry(Status st) {

        //given
        String code = "123456";
        Attempt attempt = new Attempt(UUID.randomUUID(), new Token(), st);
        ResponseStatusDto expected = new ResponseStatusDto(st, LocalDateTime.now());
        when(attemptRepo.findFirstByTokenCodeAndStatusOrderByCreatedAtDesc(code, st)).thenReturn(Optional.of(attempt));

        //when
        ResponseStatusDto actual = attemptService.lastTry(code, st);

        //then
        Assertions.assertEquals(expected.status(), actual.status());

    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"SUCCESS", "FAILED"})
    @DisplayName("бросает AttemptNotFoundException, когда attempt не найден")
    void lastTry_shouldThrowAttemptNotFoundException_whenAttemptNotFound(Status st) {

        //given
        String code = "123456";

        when(attemptRepo.findFirstByTokenCodeAndStatusOrderByCreatedAtDesc(code, st))
                .thenThrow(AttemptNotFoundException.class);

        //then
        Assertions.assertThrows(AttemptNotFoundException.class, () -> attemptService
                .lastTry(code, st));
    }
}
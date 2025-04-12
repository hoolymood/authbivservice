package com.example.authbivservice.service.impl;

import com.example.authbivservice.config.TokenProperties;
import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.AuthResultDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.dto.AuthEmailDto;
import com.example.authbivservice.domen.dto.AuthNumberDto;
import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.service.AttemptService;
import com.example.authbivservice.service.TokenService;
import com.example.authbivservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    UserService userService;
    @Mock
    AttemptService attemptService;
    @Mock
    TokenService tokenService;
    @Mock
    TokenProperties tokenProperties;

    @InjectMocks
    AuthServiceImpl authService;

    static TokenDto tokenDto;
    static Token token;

    @AfterEach
    void verifyMockInteractions() {
        verifyNoMoreInteractions(userService, attemptService, tokenService, tokenProperties);
    }

    @BeforeAll
    static void setUp() {

        UUID id = UUID.randomUUID();
        tokenDto = new TokenDto("123456");
        token = new Token(id, new User(), new ArrayList<>(), tokenDto.code());
        token.setCreatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("проверяет по эмайлу")
    void authByEmail_shouldReturnTokenDto() {

        //given
        AuthEmailDto authEmailDto = new AuthEmailDto("example@mail.ru");
        User user = new User();
        Token token = new Token();
        token.setCode("123456");
        when(userService.findByEmail(authEmailDto.email())).thenReturn(user);
        when(tokenService.create(eq(user))).thenReturn(token);

        //when
        TokenDto actual = authService.authByEmail(authEmailDto);

        //then
        Assertions.assertEquals(token.getCode(), actual.code());

    }

    @Test
    @DisplayName("проверяет по номеру")
    void authByNumber_shouldReturnTokenDto() {

        //given
        AuthNumberDto authNumberDto = new AuthNumberDto("89549162601");
        User user = new User();
        Token token = new Token();
        token.setCode("123456");
        when(userService.findByNumber(authNumberDto.number())).thenReturn(user);
        when(tokenService.create(eq(user))).thenReturn(token);

        //when
        TokenDto actual = authService.authByNumber(authNumberDto);

        //then
        Assertions.assertEquals(token.getCode(), actual.code());
    }

    @Test
    @DisplayName("возвращает статусс SUCCESS, когда токен валиден")
    void login_returnStatusSuccess_whenTokenIsValid() {

        //given
        Attempt attempt = new Attempt();
        attempt.setStatus(Status.SUCCESS);
        attempt.setToken(token);

        when(tokenService.findByCode(tokenDto.code())).thenReturn(token);
        when(tokenProperties.getAvailableAttempts()).thenReturn(2);
        when(tokenProperties.getTokenLifeTime()).thenReturn(2);
        when(tokenProperties.getRetryInterval()).thenReturn(2);
        when(attemptService.countByAfter(any(LocalDateTime.class), eq(token.getId()))).thenReturn(1L);
        when(attemptService.save(eq(attempt))).thenReturn(attempt);

        //when
        AuthResultDto actual = authService.login(tokenDto);

        //then
        Assertions.assertEquals(attempt.getStatus(), actual.status());

    }

    @Test
    @DisplayName("возвращает статус FAILED, когда окончен срок действия")
    void login_returnStatusFailed_tokenIsExpired() {

        //given
        Attempt attempt = new Attempt();
        attempt.setStatus(Status.FAILED);
        attempt.setToken(token);

        when(tokenService.findByCode(tokenDto.code())).thenReturn(token);
        when(tokenProperties.getAvailableAttempts()).thenReturn(2);
        when(tokenProperties.getTokenLifeTime()).thenReturn(0);
        when(tokenProperties.getRetryInterval()).thenReturn(2);
        when(attemptService.countByAfter(any(LocalDateTime.class), eq(token.getId()))).thenReturn(1L);
        when(attemptService.save(eq(attempt))).thenReturn(attempt);

        //when
        AuthResultDto actual = authService.login(tokenDto);

        //then
        Assertions.assertEquals(attempt.getStatus(), actual.status());

    }
}
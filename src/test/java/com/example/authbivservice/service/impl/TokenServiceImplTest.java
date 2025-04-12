package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.TokenNotFoundException;
import com.example.authbivservice.repo.TokenRepo;
import com.example.authbivservice.utils.TokenGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @Mock
    TokenRepo tokenRepo;
    @Mock
    TokenGenerator tokenGenerator;

    @InjectMocks
    TokenServiceImpl tokenService;

    @AfterEach
    void verifyMockInteractions() {
        verifyNoMoreInteractions(tokenRepo, tokenGenerator);
    }

    @Test
    @DisplayName("создает token")
    void create() {

        //given
        User user = new User(UUID.randomUUID(), "example@mail.ru", "895542355", new ArrayList<>());
        Token token = new Token();
        token.setUser(user);
        token.setCode("123456");
        when(tokenGenerator.generate()).thenReturn("123456");
        when(tokenService.save(eq(token))).thenReturn(token);

        //when
        Token actual = tokenService.create(user);

        Assertions.assertEquals(token, actual);
    }

    @Test
    @DisplayName("сохраняет token в БД")
    void save() {

        //given
        Token token = new Token();
        token.setCode("123456");
        when(tokenRepo.save(token)).thenReturn(token);

        //when
        Token actual = tokenService.save(token);

        //given
        Assertions.assertEquals(token, actual);
    }

    @Test
    @DisplayName("поиск и возврат token из БД")
    void findByCode_returnTokenWhenCodeIsValid() {

        //given
        String code = "123456";
        Token token = new Token(UUID.randomUUID(), new User(), new ArrayList<>(), code);
        when(tokenRepo.findByCode(code)).thenReturn(Optional.of(token));

        //when
        Token actual = tokenService.findByCode(code);

        //then
        Assertions.assertEquals(token, actual);
    }

    @Test
    @DisplayName("бросает TokenNotFoundException, когда token не найден по code")
    void findByCode_throwTokenNotFoundException_whenTokenNotFound() {

        //given
        String code = "123456";
        when(tokenRepo.findByCode(code)).thenThrow(TokenNotFoundException.class);

        //then
        Assertions.assertThrows(TokenNotFoundException.class, () -> tokenService.findByCode(code));

    }
}
package com.example.authbivservice.service.impl;


import com.example.authbivservice.domen.dto.AuthEmailDto;
import com.example.authbivservice.domen.dto.AuthNumberDto;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.UserNotFoundException;
import com.example.authbivservice.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserServiceImpl userService;

    static AuthEmailDto authEmailDto;
    static AuthNumberDto authNumberDto;
    static User expected;

    @BeforeAll
    static void setUp() {
        authEmailDto = new AuthEmailDto("example@mail.ru");
        authNumberDto = new AuthNumberDto("7904322548");
        expected = new User();
        expected.setEmail("example@mail.ru");
        expected.setNumber("7904322548");
    }

    @AfterEach
    void verifyMockInteractions() {
        verifyNoMoreInteractions(userRepo);
    }

    @Test
    @DisplayName("возвращает user, найденного по email")
    void findByEmail_shouldReturnUser_whenUserExist() {
        //given
        String email = "example@mail.ru";
        when(userRepo.findByEmail(email)).thenReturn(Optional.of(expected));

        //when
        User actual = userService.findByEmail(email);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("возвращает user, найденного по number")
    void findByNumber_shouldReturnUser_whenUserExist() {
        //given
        String number = "7904322548";
        when(userRepo.findByNumber(number)).thenReturn(Optional.of(expected));

        //when
        User actual = userService.findByNumber(number);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("бросает UserNotFoundException, когда user не найден по email")
    void findByEmail_throwUserNotFoundException_whenUserNotFound() {
        //given
        String email = "example@mail.ru";
        when(userRepo.findByEmail(email)).thenThrow(UserNotFoundException.class);

        //then
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findByEmail(email));
    }

    @Test
    @DisplayName("бросает UserNotFoundException, когда user не найден по number")
    void findByNumber_throwUserNotFoundException_whenUserNotFound() {
        //given
        String number = "7904322548";
        when(userRepo.findByNumber(number)).thenThrow(UserNotFoundException.class);

        //then
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findByNumber(number));
    }
}
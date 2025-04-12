package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.UserNotFoundException;
import com.example.authbivservice.log.Loggable;
import com.example.authbivservice.repo.UserRepo;
import com.example.authbivservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Loggable
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("User with email %s not exist", email)
                        ));
    }

    @Override
    public User findByNumber(String number) {
        return userRepo.findByNumber(number)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("User with number %s not exist", number)
                        ));
    }
}

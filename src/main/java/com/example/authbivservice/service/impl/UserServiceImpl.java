package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.UserNotFoundException;
import com.example.authbivservice.repo.UserRepo;
import com.example.authbivservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @Override
    public User findByEmail(AuthDto authDto) {
        return userRepo.findByEmail(authDto.email())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("User with email %s not exist", authDto.email())
                        ));
    }

    @Override
    public User findByNumber(AuthDto authDto) {
        return userRepo.findByNumber(authDto.number())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("User with number %s not exist", authDto.number())
                        ));
    }
}

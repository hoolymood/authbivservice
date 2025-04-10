package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.TypeAuth;
import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.AttemptLimitException;
import com.example.authbivservice.handler.exception.UserNotFoundException;
import com.example.authbivservice.config.TokenProperties;
import com.example.authbivservice.repo.AttemptRepo;
import com.example.authbivservice.repo.UserRepo;
import com.example.authbivservice.service.TokenService;
import com.example.authbivservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AttemptRepo attemptRepo;
    private final TokenService tokenService;
    private  final TokenProperties tokenProperties;

    @Override
    public TokenDto auth(AuthDto authDto) {

        User user = new User();

        if (authDto.typeAuth() == TypeAuth.EMAIL) {
            user = findByEmail(authDto);
        } else if (authDto.typeAuth() == TypeAuth.PHONE)
            user = findByNumber(authDto);

        Token token = tokenService.create(user);
        return new TokenDto(token.getCode());
    }


    @Override
    public Status login(TokenDto tokenDto) {

        Token token = tokenService.find(tokenDto.code());

        long countOfAttempts = attemptRepo.countByAuditCreatedAtAfter(LocalDateTime.now().minusMinutes(tokenProperties.getRetryInterval()));

        if (countOfAttempts > tokenProperties.getAvailableAttempts())
            throw new AttemptLimitException(String.format("Attempt limit exceeded (%d). Please try again later.", countOfAttempts));

        Attempt attempt = new Attempt();
        attempt.setUser(token.getUser());
        attempt.setToken(token);

        if (token.getAudit().getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(tokenProperties.getExpirationDuration()))) {
            attempt.setStatus(Status.FAILED);
        } else {
            attempt.setStatus(Status.SUCCESS);
        }

        Attempt attemptSaved = attemptRepo.save(attempt);

        return attemptSaved.getStatus();
    }

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

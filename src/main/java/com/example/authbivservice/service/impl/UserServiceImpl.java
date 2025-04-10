package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.TypeAuth;
import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.AttemptLimitException;
import com.example.authbivservice.handler.UserNotFoundException;
import com.example.authbivservice.repo.AttemptRepo;
import com.example.authbivservice.repo.UserRepo;
import com.example.authbivservice.service.TokenService;
import com.example.authbivservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AttemptRepo attemptRepo;
    private final TokenService tokenService;


    @Value("${token.availableAttempts}")
    private int availableAttempts;
    @Value("${token.expirationDuration}")
    private int expirationDuration;
    @Value("${token.retryInterval}")
    private int retryInterval;

    @Override
    public TokenDto auth(AuthDto authDto) {

        User user = new User();

        if (authDto.typeAuth() == TypeAuth.EMAIL) {
            user = userRepo.findByEmail(authDto.email())
                    .orElseThrow(
                            () -> new UserNotFoundException(
                                    String.format("User with email %s not exist", authDto.email())
                            ));
        } else if (authDto.typeAuth() == TypeAuth.PHONE)
            user = userRepo.findByNumber(authDto.number())
                    .orElseThrow(
                            () -> new UserNotFoundException(
                                    String.format("User with number %s not exist", authDto.number())
                            ));


        Token token = tokenService.create(user);
        return new TokenDto(token.getCode());
    }

    @Override
    public Status login(TokenDto tokenDto) {

        Token token = tokenService.find(tokenDto.code());

        long countOfAttempts = attemptRepo.countByAuditCreatedAtAfter(LocalDateTime.now().minusMinutes(retryInterval));

        if (countOfAttempts > availableAttempts)
            throw new AttemptLimitException(String.format("Attempt limit exceeded (%d). Please try again later.", countOfAttempts));

        Attempt attempt = new Attempt();
        attempt.setUser(token.getUser());
        attempt.setToken(token);

        if (token.getAudit().getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(expirationDuration))) {
            attempt.setStatus(Status.FAILED);
        } else {
            attempt.setStatus(Status.SUCCESS);
        }

        Attempt attemptSaved = attemptRepo.save(attempt);

        return attemptSaved.getStatus();
    }
}

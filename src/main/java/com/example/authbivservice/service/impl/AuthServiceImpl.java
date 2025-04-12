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
import com.example.authbivservice.handler.exception.AttemptLimitException;
import com.example.authbivservice.service.AttemptService;
import com.example.authbivservice.service.AuthService;
import com.example.authbivservice.service.TokenService;
import com.example.authbivservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AttemptService attemptService;
    private final TokenService tokenService;
    private final TokenProperties tokenProperties;


    @Override
    public TokenDto authByEmail(AuthEmailDto authDto) {

        User user = userService.findByEmail(authDto.email());
        return new TokenDto(tokenService.create(user).getCode());
    }

    @Override
    public TokenDto authByNumber(AuthNumberDto authDto) {

        User user = userService.findByNumber(authDto.number());
        return new TokenDto(tokenService.create(user).getCode());
    }


    @Override
    @Transactional
    public AuthResultDto login(TokenDto tokenDto) {

        Token token = tokenService.findByCode(tokenDto.code());

        LocalDateTime since = LocalDateTime.now().minusMinutes(tokenProperties.getRetryInterval());
        long countOfAttempts = attemptService.countByAfter(since, token.getId());

        if (countOfAttempts >= tokenProperties.getAvailableAttempts())
            throw new AttemptLimitException(String.format("Attempt limit exceeded (%d). Please try again later.", countOfAttempts));

        Attempt attempt = new Attempt();
        attempt.setToken(token);

        if (token.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(tokenProperties.getTokenLifeTime()))) {
            attempt.setStatus(Status.SUCCESS);
        } else attempt.setStatus(Status.FAILED);

        Attempt attemptSaved = attemptService.save(attempt);
        return new AuthResultDto(attemptSaved.getStatus());
    }
}

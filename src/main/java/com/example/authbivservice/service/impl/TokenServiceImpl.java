package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.ConflictCodeException;
import com.example.authbivservice.handler.exception.DuplicateCodeException;
import com.example.authbivservice.handler.exception.TokenNotFoundException;
import com.example.authbivservice.log.Loggable;
import com.example.authbivservice.repo.TokenRepo;
import com.example.authbivservice.service.TokenService;
import com.example.authbivservice.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Loggable
public class TokenServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;
    private final TokenGenerator tokenGenerator;

    @Retryable(label = "Повторная генерация кода при коллизиях",
            retryFor = DuplicateCodeException.class)
    @Override
    public Token create(User user) {

        Token token = new Token();
        token.setUser(user);
        String code = tokenGenerator.generate();

        tokenRepo.findByCodeAndUserId(code, user.getId()).ifPresent(a -> {
            throw new DuplicateCodeException("the code is already there");
        });

        token.setCode(code);
        return save(token);
    }

    @Override
    public Token findByCode(String code) {
        return tokenRepo.findByCode(code)
                .orElseThrow(
                        () -> new TokenNotFoundException(
                                String.format("Token %s does not exist", code)
                        ));
    }

    @Override
    public Token save(Token token) {
        return tokenRepo.save(token);
    }

    @Recover
    public Token handleDuplicateCode(DuplicateCodeException e) {
        throw new ConflictCodeException("Please try again later");
    }
}

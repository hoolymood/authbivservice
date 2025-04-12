package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.exception.TokenNotFoundException;
import com.example.authbivservice.repo.TokenRepo;
import com.example.authbivservice.service.TokenService;
import com.example.authbivservice.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;
    private final TokenGenerator tokenGenerator;

    @Override
    public Token create(User user) {

        Token token = new Token();
        token.setUser(user);
        token.setCode(tokenGenerator.generate());

        return save(token);
    }

    @Override
    public Token findByCode(String code) {
        return tokenRepo.findByCode(code)
                .orElseThrow(
                        () -> new TokenNotFoundException(
                                String.format("Token %s not exist", code)
                        ));
    }

    public Token save(Token token) {
        return tokenRepo.save(token);
    }
}

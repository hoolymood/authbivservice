package com.example.authbivservice.service.impl;

import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;
import com.example.authbivservice.handler.TokenNotFoundException;
import com.example.authbivservice.repo.TokenRepo;
import com.example.authbivservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;

    @Override
    public Token create(User user) {

        Token token = new Token();
        token.setUser(user);
        token.setCode("RANDOM CODE" + user.getId());

        return tokenRepo.save(token);
    }

    @Override
    public Token find(String code) {

        return tokenRepo.findByCode(code)
                .orElseThrow(
                        () -> new TokenNotFoundException(
                                String.format("Token %s not exist", code)
                        ));
    }
}

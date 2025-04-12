package com.example.authbivservice.service;

import com.example.authbivservice.domen.dto.AuthResultDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.dto.AuthEmailDto;
import com.example.authbivservice.domen.dto.AuthNumberDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResultDto login(TokenDto tokenDto);
    TokenDto authByEmail(AuthEmailDto authEmailDto);
    TokenDto authByNumber(AuthNumberDto authNumberDto);
}

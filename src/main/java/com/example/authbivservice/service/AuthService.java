package com.example.authbivservice.service;

import com.example.authbivservice.domen.dto.AuthAResultDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.dto.request.AuthEmailDto;
import com.example.authbivservice.domen.dto.request.AuthNumberDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthAResultDto login(TokenDto tokenDto);
    TokenDto authByEmail(AuthEmailDto authEmailDto);
    TokenDto authByNumber(AuthNumberDto authNumberDto);
}

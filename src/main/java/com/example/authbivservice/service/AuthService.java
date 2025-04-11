package com.example.authbivservice.service;

import com.example.authbivservice.domen.dto.AuthAResultDto;
import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.dto.TokenDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    TokenDto auth(AuthDto authDto);
    AuthAResultDto login(TokenDto tokenDto);
}

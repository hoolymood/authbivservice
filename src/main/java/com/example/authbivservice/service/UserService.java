package com.example.authbivservice.service;

import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.TokenDto;

public interface UserService {

    TokenDto auth(AuthDto authDto);
    Status login(TokenDto tokenDto);
}

package com.example.authbivservice.service;

import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.entity.User;

public interface UserService {

    TokenDto auth(AuthDto authDto);
    User findByNumber(AuthDto authDto);
    User findByEmail(AuthDto authDto);
    Status login(TokenDto tokenDto);
}

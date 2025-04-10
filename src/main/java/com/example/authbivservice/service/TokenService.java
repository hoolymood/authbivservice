package com.example.authbivservice.service;

import com.example.authbivservice.domen.entity.Token;
import com.example.authbivservice.domen.entity.User;

public interface TokenService {

    Token create(User user);
    Token find(String code);
}

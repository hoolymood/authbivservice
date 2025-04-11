package com.example.authbivservice.service;

import com.example.authbivservice.domen.entity.User;

public interface UserService {

    User findByNumber(String number);
    User findByEmail(String email);

}

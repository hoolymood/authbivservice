package com.example.authbivservice.controller;

import com.example.authbivservice.domen.dto.AuthDto;
import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/auth")
    public TokenDto auth(@RequestBody AuthDto authDto) {
        return userService.auth(authDto);
    }

    @PostMapping("/login")
    public Status login(@RequestBody TokenDto tokenDto) {
        return userService.login(tokenDto);

    }
}

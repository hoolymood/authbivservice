package com.example.authbivservice.controller;

import com.example.authbivservice.domen.dto.AuthAResultDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.domen.dto.request.AuthEmailDto;
import com.example.authbivservice.domen.dto.request.AuthNumberDto;
import com.example.authbivservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping ("/number")
    public TokenDto authByNumber(@RequestBody AuthNumberDto authDto) {
        return authService.authByNumber(authDto);
    }

    @PostMapping ("/email")
    public TokenDto authByEmail(@RequestBody AuthEmailDto authDto) {
        return authService.authByEmail(authDto);
    }

    @PostMapping("/login")
    public AuthAResultDto login(@RequestBody TokenDto tokenDto) {
        return authService.login(tokenDto);
    }
}

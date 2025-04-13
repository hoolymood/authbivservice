package com.example.authbivservice.controller;

import com.example.authbivservice.domen.dto.AuthEmailDto;
import com.example.authbivservice.domen.dto.AuthNumberDto;
import com.example.authbivservice.domen.dto.AuthResultDto;
import com.example.authbivservice.domen.dto.TokenDto;
import com.example.authbivservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер AuthController", description = "Обрабатывает связанные с аутентификацией и авторизацией эндпоинты.")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Авторизация пользователя",
            description = "Авторизация по номеру",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная авторизация"),
                    @ApiResponse(responseCode = "404", description = "Пользователя с таким номером не существует", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Не удалось сгенерировать код", content = @Content)
            })
    @PostMapping("/number")
    public TokenDto authByNumber(@RequestBody @Valid AuthNumberDto authDto) {
        return authService.authByNumber(authDto);
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "Авторизация по эмайл",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная авторизация"),
                    @ApiResponse(responseCode = "404", description = "Пользователь с таким эмайл не существует", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Не удалось сгенерировать код", content = @Content)
            })
    @PostMapping("/email")
    public TokenDto authByEmail(@RequestBody @Valid AuthEmailDto authDto) {
        return authService.authByEmail(authDto);
    }

    @Operation(
            summary = "Аутентификация",
            description = "Аутентификация по токену",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная аутентификация"),
                    @ApiResponse(responseCode = "404", description = "Токен не найден", content = @Content),
                    @ApiResponse(responseCode = "429", description = "Слишком много запросов по токену", content = @Content)
            })
    @PostMapping("/login")
    public AuthResultDto login(@RequestBody @Valid TokenDto tokenDto) {
        return authService.login(tokenDto);
    }
}

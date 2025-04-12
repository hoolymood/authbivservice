package com.example.authbivservice.controller;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.ResponseStatusDto;
import com.example.authbivservice.service.AttemptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attempts")
@Tag(name = "Контроллер AttemptController ", description = "Обрабатывает эндпоинты связанные с доменом Attempts.")
public class AttemptController {

    private final AttemptService attemptService;

    @Operation(
            summary = "Получение попытки",
            description = "Получение последней попытки по токену",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное получение ответа"),
                    @ApiResponse(responseCode = "404", description = "Попытка не найдена", content = @Content)
            })
    @GetMapping("/{code}")
    public ResponseStatusDto lastTry(
            @PathVariable("code") String code,
            @RequestParam(defaultValue = "SUCCESS") Status status) {
        return attemptService.lastTry(code, status);
    }

}

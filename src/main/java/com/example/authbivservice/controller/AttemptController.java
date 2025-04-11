package com.example.authbivservice.controller;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.ResponseStatusDto;
import com.example.authbivservice.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class AttemptController {

    private final AttemptService attemptService;

    @GetMapping("/{code}")
    public ResponseStatusDto lastTry(@PathVariable ("code") String code,
                                     @RequestParam(defaultValue = "SUCCESS") Status status) {
        return attemptService.lastTry(code, status);
    }

}

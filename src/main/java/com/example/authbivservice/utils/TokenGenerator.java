package com.example.authbivservice.utils;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    /**
     * Метод для генерации токена.
     *
     * Условия генерации:
     * - Количество цифр: 6
     * - Отсутствуют лидирующие нули
     * - Отсутствует повторение соседних цифр
     *
     * @return String сгенерированный код
     */
    public String generate() {
        int randomCode = (int) ((Math.random() * (999999 - 100000)) + 100000);
        return String.valueOf(randomCode);
    }
}

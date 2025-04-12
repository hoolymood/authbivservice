package com.example.authbivservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс хранит основные значения для проверки валидности токена.
 * Он содержит необходимые параметры для валидации и управления токенами.
 */

@Setter
@Getter
@ConfigurationProperties(prefix = "token")
public class TokenProperties {
    /**
     * Доступные попытки в течении retryInterval.
     */
    private int availableAttempts;
    /**
     * Время жизни токена в минутах.
     */
    private int tokenLifeTime;
    /**
     * Интервал для проверки доступных попыток в минутах
     */
    private int retryInterval;
}

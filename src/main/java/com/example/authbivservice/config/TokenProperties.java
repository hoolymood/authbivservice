package com.example.authbivservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "token")
public class TokenProperties {
    private int availableAttempts;
    private int tokenLifeTime;
    private int retryInterval;
}

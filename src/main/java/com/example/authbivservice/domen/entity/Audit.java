package com.example.authbivservice.domen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class Audit {

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
}

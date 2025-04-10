package com.example.authbivservice.domen.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "usr_t")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "email")
    private String email;

    @Column (name = "number")
    private String number;

    @OneToMany (mappedBy = "user",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Token> tokens;

    @OneToMany (mappedBy = "user",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Attempt> attempts;
}

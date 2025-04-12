package com.example.authbivservice.repo;

import com.example.authbivservice.domen.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepo extends JpaRepository<Token, UUID> {

    Optional<Token> findByCode(String code);
    Optional<Token> findByCodeAndUserId(String code, UUID userId);

}

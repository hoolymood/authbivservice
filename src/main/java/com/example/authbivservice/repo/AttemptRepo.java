package com.example.authbivservice.repo;

import com.example.authbivservice.domen.entity.Attempt;
import com.example.authbivservice.domen.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface AttemptRepo extends JpaRepository<Attempt, UUID> {

    @Query ("""
            SELECT count(a)
            FROM Attempt a
            WHERE a.createdAt > :since
            AND a.token.id = :tokenId
            """)
    long countByAuditCreatedAtAfterAndByToken(LocalDateTime since, UUID tokenId);
}

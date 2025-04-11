package com.example.authbivservice.repo;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttemptRepo extends JpaRepository<Attempt, UUID> {

    @Query("""
            SELECT count(a)
            FROM Attempt a
            WHERE a.createdAt > :since
            AND a.token.id = :tokenId
            """)
    long countByAuditCreatedAtAfterAndByToken(LocalDateTime since, UUID tokenId);

    Optional<Attempt> findFirstByTokenCodeAndStatusOrderByCreatedAtDesc(String code, Status status);
}

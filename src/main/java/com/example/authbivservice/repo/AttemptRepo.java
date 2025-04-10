package com.example.authbivservice.repo;

import com.example.authbivservice.domen.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface AttemptRepo extends JpaRepository<Attempt, UUID> {

    long countByAuditCreatedAtAfter(LocalDateTime since);
}

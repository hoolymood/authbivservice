package com.example.authbivservice.repo;

import com.example.authbivservice.domen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository <User, UUID> {
    Optional <User> findByEmail (String email);
    Optional <User> findByNumber (String number);
}

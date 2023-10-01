package com.seboonline.repositories;

import com.seboonline.models.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Transactional
    void deleteAllByUserId(UUID id);

    boolean existsByToken(String token);
}

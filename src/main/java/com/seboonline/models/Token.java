package com.seboonline.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tokens")
public class Token {

    @Id
    @Column(name = "token_id")
    private UUID id;

    @Column(nullable = false, name="user_id")
    private UUID userId;

    @Column(nullable = false, length = 600, name = "access_token")
    private String token;

    public Token(UUID userId, String token){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.token = token;
    }
}

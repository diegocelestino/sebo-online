package com.seboonline.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "item_id")
    private UUID itemId;

    @Column(nullable = false, length = 60)
    private String title;

    private byte[] image;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false, length = 1200)
    private String description;

    private Boolean active;

    @Column(length = 15)
    private String edition;

    @Column(length = 30)
    private String frequency;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

}

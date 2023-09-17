package com.seboonline.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    private UUID id;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private Timestamp createDate;

    @OneToOne
    private User buyer;

    @OneToOne
    private User seller;

    @OneToOne
    private Item item;

}

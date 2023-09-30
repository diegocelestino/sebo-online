package com.seboonline.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private UUID id;

    private Timestamp date;

    @Column(name = "buyer_id", nullable = false)
    private UUID buyerId;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;
}

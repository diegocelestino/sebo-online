package com.seboonline.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "items")
public class Item {
    @Id
    private UUID id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String name;

    @Column()
    private byte[] image;

    @ManyToOne
    private Category category;

    @Column()
    private String author;

    @Column()
    private BigDecimal price;

    @Column()
    private String description;

    @Column()
    private Boolean active;

    @Column()
    private String edition;

    @Column
    private String frequency;


}

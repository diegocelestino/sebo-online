package com.seboonline.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "author_id")
    private UUID id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

}

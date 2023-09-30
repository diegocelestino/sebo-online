package com.seboonline.repositories;

import com.seboonline.models.Author;
import com.seboonline.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}

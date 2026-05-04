package com.example.milestone.repositories;

import com.example.milestone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByUserId(UUID userId);

    Optional<Product> findByIdAndUserId(UUID id, UUID userId);

    @Transactional
    long deleteByIdAndUserId(UUID id, UUID userId);
}

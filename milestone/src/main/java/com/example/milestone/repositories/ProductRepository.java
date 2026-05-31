package com.example.milestone.repositories;

import com.example.milestone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides the access methods for database of Product objects.
 * 
 * The repository uses Spring Data JPA to retrieve and update product records
 * in the database.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	/**
	 * Finds all products owned by specified user.
	 * 
	 * @param userId the id of the user who owns the products
	 * @return a list of products owned by the user
	 */
    List<Product> findByUserId(UUID userId);

    /**
     * Finds a product via its id if owned by a specific user.
     * 
     * @param id the id of the product
     * @param userId the id of the user who owns the product
     * @return product if found, empty if not found
     */
    Optional<Product> findByIdAndUserId(UUID id, UUID userId);

    /**
     * Deletes a product by its specified id.
     * 
     * @param id the id of the product
     * @param userId the id of the user who owns the product
     * @return the number of rows deleted
     */
    @Transactional
    long deleteByIdAndUserId(UUID id, UUID userId);
}

package com.example.milestone.services;

import com.example.milestone.models.Product;
import com.example.milestone.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides business logic for product-related operations.
 * 
 * This service returns, creates, updates, and deletes products
 * for a user.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Creates a ProductService with the product repository.
     * 
     * @param productRepository the repository to hold product data
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns all the products owned by a specific user.
     * 
     * @param userId the id of the user who owns the products.
     * @return a list of products
     */
    public List<Product> getAllProducts(UUID userId) {
        return productRepository.findByUserId(userId);
    }

    /**
     * Returns a product matching a specific id.
     * 
     * @param id the id of the product
     * @param userId the id of the user who owns the product
     * @return product if found, null if not
     */
    public Product getProductById(UUID id, UUID userId) {
        return productRepository.findByIdAndUserId(id, userId).orElse(null);
    }

    /**
     * Saves a new product to the database.
     * 
     * @param product the product to add
     * @return the saved product
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product via product id.
     * 
     * @param id the id of the product to update
     * @param userId the id of the user who owns the product
     * @param updated the new updated product
     * @return true if product is updated, otherwise fail
     */
    public boolean updateProduct(UUID id, UUID userId, Product updated) {
        Optional<Product> existing = productRepository.findByIdAndUserId(id, userId);
        if (existing.isEmpty()) return false;

        Product product = existing.get();
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setSku(updated.getSku());
        product.setPrice(updated.getPrice());
        product.setQuantity(updated.getQuantity());
        productRepository.save(product);
        return true;
    }

    /**
     * Deletes a specific product.
     * 
     * @param id the id of the product to be deleted
     * @param userId the id of the user who owns the product
     * @return true if product is deleted, otherwise false
     */
    public boolean deleteProduct(UUID id, UUID userId) {
        return productRepository.deleteByIdAndUserId(id, userId) > 0;
    }
}

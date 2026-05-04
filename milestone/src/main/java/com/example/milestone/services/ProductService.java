package com.example.milestone.services;

import com.example.milestone.models.Product;
import com.example.milestone.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(UUID userId) {
        return productRepository.findByUserId(userId);
    }

    public Product getProductById(UUID id, UUID userId) {
        return productRepository.findByIdAndUserId(id, userId).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

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

    public boolean deleteProduct(UUID id, UUID userId) {
        return productRepository.deleteByIdAndUserId(id, userId) > 0;
    }
}

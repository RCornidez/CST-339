package com.example.milestone.services;

import com.example.milestone.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts(UUID userId) {
        return products.stream()
                .filter(p -> p.getUserId().equals(userId))
                .toList();
    }

    public Product getProductById(UUID id, UUID userId) {
        return products.stream()
                .filter(p -> p.getId().equals(id) && p.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        product.setId(UUID.randomUUID());
        products.add(product);
    }

    public boolean updateProduct(UUID id, UUID userId, Product updated) {

        for (int i = 0; i < products.size(); i++) {

            Product existing = products.get(i);

            if (existing.getId().equals(id) && existing.getUserId().equals(userId)) {
                updated.setId(id);
                updated.setUserId(userId);
                products.set(i, updated);
                return true;
            }
        }

        return false;
    }

    public boolean deleteProduct(UUID id, UUID userId) {
        return products.removeIf(p -> p.getId().equals(id) && p.getUserId().equals(userId));
    }
}

package com.example.milestone.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull(message = "Product name is a required field")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 200, message = "Description must be less than 200 characters")
    @Column(name = "description")
    private String description;

    @NotNull(message = "SKU is a required field")
    @Size(min = 1, max = 50, message = "SKU must be between 1 and 50 characters")
    @Column(name = "sku", nullable = false)
    private String sku;

    @NotNull(message = "Price is a required field")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be 0.00 or greater")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Min(value = 0, message = "Quantity must be 0 or greater")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Product() {
    }

    public Product(UUID id, UUID userId, String name, String description, String sku, BigDecimal price, int quantity) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

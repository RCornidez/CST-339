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

/**
 * Model representing a product that is stored in the database.
 * 
 * This class maps products to the product table with their 
 * name, description, sku number, price, quantity, 
 * and user (owner of product)
 */
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

    /**
     * Default constructor.
     */
    public Product() {
    }

    /**
     * Initializes a product with specified fields.
     * 
     * @param id unique id of product
     * @param userId id of user that owns product
     * @param name the name of product
     * @param description the description of product
     * @param sku Stock Keeping Unit code of product
     * @param price the price of product
     * @param quantity the amount of products stored
     */
    public Product(UUID id, UUID userId, String name, String description, String sku, BigDecimal price, int quantity) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns product id.
     * 
     * @return product id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets product id.
     * 
     * @param id is id of product
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns user id of owner.
     * 
     * @return user id
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets the id of user (owner).
     * 
     * @param userId the id of owner user
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns name of product.
     * 
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * 
     * @param name of product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns product description.
     * 
     * @return description of product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of product.
     * 
     * @param description of product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns product sku number.
     * 
     * @return sku of product
     */
    public String getSku() {
        return sku;
    }

    /**
     * Sets the sku number of product.
     * 
     * @param sku the sku number of specified product
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Returns price of product.
     * 
     * @return price the price of product
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price of product.
     * 
     * @param price is price of product
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns quantity of product.
     * 
     * @return quantity the quantity of product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity of a product.
     * 
     * @param quantity the quantity of product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

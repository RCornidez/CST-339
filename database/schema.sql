CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

CREATE TABLE users (
    id         CHAR(36)     PRIMARY KEY DEFAULT (UUID()),
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE TABLE products (
    id          CHAR(36)      PRIMARY KEY DEFAULT (UUID()),
    user_id     CHAR(36)      NOT NULL,
    name        VARCHAR(100)  NOT NULL,
    description TEXT,
    sku         VARCHAR(50)   NOT NULL,
    price       DECIMAL(10,2) NOT NULL,
    quantity    INT           NOT NULL DEFAULT 0,
    created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uq_products_user_sku UNIQUE (user_id, sku),
    CONSTRAINT fk_products_user     FOREIGN KEY (user_id) REFERENCES users(id)
);

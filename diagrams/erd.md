```mermaid
erDiagram
    USERS {
        CHAR(36) id PK
        VARCHAR(50) first_name
        VARCHAR(50) last_name
        VARCHAR(100) email UK
        VARCHAR(255) password
        DATETIME created_at
    }

    PRODUCTS {
        CHAR(36) id PK
        CHAR(36) user_id FK
        VARCHAR(100) name
        TEXT description
        VARCHAR(50) sku
        DECIMAL(10_2) price
        INT quantity
        DATETIME created_at
        DATETIME updated_at
    }

    USERS ||--o{ PRODUCTS : "owns"
```


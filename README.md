# pati — Order Management System (Phase 1)

`pati` is an Order Management REST service built with Spring Boot 3.4.2, Java 21, and Gradle.

## Architecture

Strict multi-tier layering:
- **Controllers (`com.pm.pati.controller`)**: Handles HTTP routing, request validation, and status codes.
- **Services (`com.pm.pati.service`)**: Implements business rules (stock check, stock deduction, validation).
- **Repositories (`com.pm.pati.repository`)**: Data access layer backed by Spring Data JPA.
- **DTOs (`com.pm.pati.dto`)**: Separate Request and Response DTOs ensuring JPA Entities are never exposed over HTTP.

---

## Setup & Running

### 1. Run LocalStack Container
To run PostgreSQL via LocalStack Pro with Docker Compose:

```bash
$env:LOCALSTACK_AUTH_TOKEN="your_token_here" # In PowerShell
docker-compose up -d
```

### 2. Run Spring Boot Application
```bash
.\gradlew bootRun
```

### 3. Run Tests
```bash
.\gradlew test
```

---

## API Endpoints & Example Curl Commands

### Products API (`/products`)

#### 1. Create Product
```bash
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mechanical Keyboard",
    "description": "RGB Mechanical Keyboard with Blue Switches",
    "price": 89.99,
    "stockQuantity": 25
  }'
```

#### 2. Get All Products
```bash
curl -X GET http://localhost:8080/products
```

#### 3. Get Product By ID
```bash
curl -X GET http://localhost:8080/products/1
```

#### 4. Update Product
```bash
curl -X PUT http://localhost:8080/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Wireless Mechanical Keyboard",
    "description": "RGB Wireless Mechanical Keyboard",
    "price": 99.99,
    "stockQuantity": 30
  }'
```

#### 5. Delete Product
```bash
curl -X DELETE http://localhost:8080/products/1
```

---

### Orders API (`/orders`)

#### 1. Create Order (Placing an Order & Deducting Stock)
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": 2
  }'
```

#### 2. Get Order By ID
```bash
curl -X GET http://localhost:8080/orders/1
```

#### 3. Get Orders (Paginated)
```bash
curl -X GET "http://localhost:8080/orders?page=0&size=10"
```

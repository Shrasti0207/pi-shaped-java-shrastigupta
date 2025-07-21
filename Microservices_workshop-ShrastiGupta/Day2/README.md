# Shrasti Gupta

## 📅 Day 2 - Spring Cloud Gateway & Config Server

---

### 🧩 Objective:
Design and deploy a microservice architecture using:
- ✅ Spring Cloud Gateway for routing
- ✅ Spring Cloud Config Server for centralized configuration
- ✅ Role-based security
- ✅ Dynamic config refresh
- ✅ Logging with filters

---


### 🔗 External Config Repository

This project uses a centralized config repository managed via Spring Cloud Config Server.

📁 Public GitHub config repo:
**[https://github.com/Shrasti0207/configuration-repo.git]**

It includes:
- `product-service.yml`
- `order-service.yml`
- `spring-cloud-gateway.yml`

---

### 🧪 How to Run

1. **Start Config Server**
   ```bash
   cd config-server
   ./mvnw spring-boot:run
   ```
2. **Start Product & Order Services**
    ```bash
    cd product-service
    ./mvnw spring-boot:run
    
    cd order-service
    ./mvnw spring-boot:run
    
    ```

3. **Start Spring Cloud Gateway**
    ```bash
    cd spring-cloud-gateway
    ./mvnw spring-boot:run
   ```
---
## ✅ Submission Checklist

| Task                                                       | Status |
|------------------------------------------------------------|--------|
| Spring Cloud Config Server loads from Git                  | ✅     |
| GET /products/** routes to product-service                 | ✅     |
| GET /orders/** routes to order-service                     | ✅     |
| Dynamic config reload via POST /actuator/refresh           | ✅     |
| Gateway logs incoming requests via custom filter           | ✅     |
| /admin/** route is protected with ROLE_ADMIN               | ✅     |

---

## 🔐 Sample Auth Users

| Username | Password  | Role       |
|----------|-----------|------------|
| admin    | admin123  | ROLE_ADMIN |
| user     | user123   | ROLE_USER  |

➡️ **Try accessing:**  
`http://localhost:8080/admin/info`  
You’ll be prompted for credentials.

---

## 📘 Core Concept Questions

### 1. What is the purpose of `bootstrap.yml` in a config client?
- It is loaded **before** `application.yml`.
- Used to configure the **Spring Cloud Config Server URI**, application name, and profile.
- Essential for **fetching external configurations** early in the startup phase.
---

### 2. How does `@RefreshScope` work and when should it be used?
- Marks beans that should be **reloaded at runtime** when a config changes.
- Used with **Spring Cloud Bus** and `/actuator/refresh`.
- Apply to services whose config needs **runtime update** (e.g., message text, limits).

---

### 3. What’s the difference between static and dynamic route configuration in Gateway?

- **Static**: Hardcoded in `application.yml`
- **Dynamic**: Fetched from config server (YAML in Git), database, or service discovery

---

### 4. How does rate limiting in Spring Cloud Gateway work internally?
- Uses **Redis** as a token bucket store (via `RedisRateLimiter`).
- Controls requests per second using **tokens and replenish rate**.
- Applied via **Gateway filters** on specific routes.
---

### 5. How do you test a configuration change without restarting services?

- Update config in Git (or repo), trigger `/actuator/refresh` or use **Spring Cloud Bus** for auto-propagation.
- `@RefreshScope` beans reload with new values.

---

### 6. What’s the difference between global and per-route filters in Gateway?

- **Global Filters**: Apply to **all routes**
- **Per-route Filters**: Apply only to specific routes as defined in config

---

### 7. How does Spring Cloud Bus enhance dynamic config refresh?
It uses **message brokers** like RabbitMQ/Kafka to **broadcast refresh events** to all services, so no need to manually call `/actuator/refresh`.

---

### 8. How is JWT verified at the Gateway level?
- Use a **custom filter** or **Spring Security filter** in Gateway.
- JWT token is extracted from headers → verified (signature, expiry) → claims extracted → request forwarded.

---

### 9. What’s the role of service discovery when routing via Gateway?
- Gateway uses **DiscoveryClient (e.g., Eureka)** to dynamically resolve service URLs.
- Allows **load-balanced routing** without hardcoding service addresses.

---
### 10. Why prefer centralized config and not embedded .yml?
- Enables **externalized, dynamic configuration**.
- Central control for **all environments** (dev, QA, prod).
- Easier maintenance, versioning, and auditing of changes via Git or Vault.


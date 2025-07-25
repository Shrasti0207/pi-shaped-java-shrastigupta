# Microservices Architecture with Service Discovery

**Author**: Shrasti Gupta

This project demonstrates a complete microservices-based architecture using **Spring Boot**, **Spring Cloud Eureka**, and **Spring Cloud Gateway** for service registration, discovery, and routing.

---

## How It Works

- All services register themselves with Eureka using `@EnableEurekaClient`.
- The API Gateway uses `lb://<service-name>` URI format to route requests dynamically.
- Eureka provides instance registration, discovery, load balancing, and failure detection.

---

## Sample Gateway Route (`application.yml`)

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/users/**
```
---

## Service Health with Spring Boot Actuator

Each service exposes the `/actuator/health` endpoint:

- Eureka uses it to decide if a service instance is healthy.
- Ensures only healthy services receive traffic.
- You can also expose `/actuator/info`, `/actuator/metrics`, etc.

### Example Configuration:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info
```
---


## Core Concept Questions (Explained in Simple Terms)

### 1. What’s the difference between client-side and server-side service discovery?

- In **client-side discovery** (e.g., Eureka), the client queries the registry and picks a service instance.
- In **server-side discovery** (e.g., Kubernetes), a load balancer (like kube-proxy) routes requests on the client’s behalf.

### 2. How does Eureka detect dead service instances?

- Eureka uses **heartbeats** (default every 30 seconds) from clients.
- If no heartbeat is received within a timeout period (default 90 seconds), the instance is marked as unavailable.

### 3. Why is the `/actuator/health` endpoint important for service discovery?
- Eureka (or any registry) checks `/actuator/health` to determine service health.
- If the health status is `DOWN`, the service may be removed or not used in routing.

### 4. How does `lb://` routing in Gateway work with DiscoveryClient?
- The `lb://service-name` URI tells Spring Cloud Gateway to resolve the service via **DiscoveryClient**.
- It uses Spring LoadBalancer to select an available instance dynamically.

### 5. What happens when a registered service goes down?
- If a service stops sending heartbeats or fails health checks, it is marked as **unavailable**.
- Load balancers will stop routing traffic to it until it's healthy again.

### 6. Compare Eureka, Consul, and Kubernetes DNS-based discovery.
- **Eureka**: Java-friendly, client-side, Netflix OSS.
- **Consul**: Cross-platform, supports health checks and key/value config.
- **Kubernetes DNS**: Server-side discovery using DNS resolution via cluster DNS (CoreDNS).

### 7. How does Spring LoadBalancer choose which instance to call?
- By default, it uses a **Round-Robin** strategy.
- You can customize it with **Weighted Response Time**, **Random**, or **custom rules**.

## Prerequisites

- Java 17+
- Maven
- Spring Boot with Spring Cloud
- Docker *(optional, for containerization)*
- Postman *(for testing APIs)*

---

## Running the Project

1. Start the **Eureka Server**
2. Start `user-service` and `order-service` (they auto-register with Eureka)
3. Start the **API Gateway**
4. Test via Postman or browser:
    - [http://localhost:8080/users](http://localhost:8080/users)
    - [http://localhost:8080/orders](http://localhost:8080/orders)

---




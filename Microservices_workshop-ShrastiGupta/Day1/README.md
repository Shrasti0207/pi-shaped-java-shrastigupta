# Microservices Workshop Day 1 - Shrasti Gupta

The project focuses on building a cloud-native e-commerce backend using **Spring WebFlux**, **gRPC**, **R2DBC**, **Redis**, **SSE**, and **WebSocket** technologies.

---


### 1. What are the differences between Mono and Flux, and where did you use each?
- **Mono**: Emits 0 or 1 item (similar to `Optional`).
- **Flux**: Emits 0 to N items (similar to a `List`).
- **Usage**:
  - `Mono` for single item responses (e.g., find by ID).
  - `Flux` for streams/multiple items (e.g., fetch all records).

---

### 2. How does R2DBC differ from traditional JDBC?
- **R2DBC**: Reactive and non-blocking.
- **JDBC**: Blocking and synchronous.
- **R2DBC** is used in reactive stacks like Spring WebFlux for full non-blocking I/O.

---

### 3. How does Spring WebFlux routing differ from `@RestController`?
- **@RestController**: Annotation-based, declarative.
- **WebFlux Routing**: Functional, uses `RouterFunction` and `HandlerFunction`.
- **Routing is better** for lightweight, programmatic control and functional style APIs.

---

### 4. What are the advantages of using `@Transactional` with R2DBC (or why not)?
- R2DBC doesn’t fully support `@Transactional` out of the box.
- Use **programmatic transactions** via `ConnectionFactory`.
- `@Transactional` is mostly ineffective unless explicitly configured for reactive.

---

### 5. Explain optimistic vs pessimistic locking and when to use each.
- **Optimistic Locking**: No lock, uses versioning. Use when **low contention** is expected.
- **Pessimistic Locking**: Locks data to avoid conflict. Use when **high contention** is likely.

---

### 6. How does session management work using Redis in reactive apps?
- Use `spring-session-data-redis` with reactive support.
- Session data is stored in Redis for stateless, scalable session handling.

---

### 7. How did you expose and verify SSE updates?
- Use `Flux` with `MediaType.TEXT_EVENT_STREAM`.
- Clients consume using `curl`, browser `EventSource`, or WebTestClient.
- Useful for real-time one-way updates (e.g., notifications, stock prices).

---

### 8. What is the role of Swagger/OpenAPI in CI/CD and API tooling?
- Generates interactive API docs.
- Used for **contract validation** during builds.
- Enables API mocking, code generation, and frontend-backend integration.

---

### 9. How does WebTestClient differ from MockMvc in testing?
- **WebTestClient**: Used with WebFlux; fully non-blocking.
- **MockMvc**: Used with Spring MVC; blocking.
- WebTestClient can test real reactive HTTP layers or mock handlers.

---

### 10. Compare WebSocket, SSE, and RSocket for real-time data.
| Protocol    | Direction     | Use Case              | Features                           |
|-------------|---------------|------------------------|------------------------------------|
| **WebSocket** | Bi-directional | Chat apps, games        | Full duplex, fast, persistent      |
| **SSE**       | Server → Client | Notifications, updates  | Lightweight, HTTP-based, one-way   |
| **RSocket**   | Bi-directional | Reactive microservices | Backpressure, multiplexing, binary |

---
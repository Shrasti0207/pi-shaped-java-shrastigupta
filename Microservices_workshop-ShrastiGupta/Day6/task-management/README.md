# Spring Boot Security - Shrasti Gupta

A **Task Management System** built using **Spring Boot** that allows users to manage tasks efficiently with features like task creation, updates, deletion, and filtering by status, priority, and project.

---

## Features

- **CRUD Operations** for tasks, projects, and users
- **Filtering by Status, Priority, and Project**
- **Assigning Tasks to Users**
- **User Management with Role-Based Access**
- **Exception Handling with Custom Errors**
- **Spring Data JPA for Database Operations**
- **RESTful API with JSON Responses**
- **Optional Handling for Null Values**

## Getting Started
**Access the API** at `http://localhost:8080`

---

## API Endpoints

### **Task Management APIs**

| Method  | Endpoint                         | Description                  |
|---------|----------------------------------|------------------------------|
| **POST**  | `/tasks`                        | Create a new task            |
| **GET**   | `/tasks`                        | Get all tasks                |
| **GET**   | `/tasks/{id}`                   | Get task by ID               |
| **PUT**   | `/tasks/{id}`                   | Update task                  |
| **DELETE**| `/tasks/{id}`                   | Delete task                  |
| **PATCH** | `/tasks/{id}/status?status=X`   | Update task status           |
| **GET**   | `/tasks/status/{status}`        | Get tasks by status          |
| **GET**   | `/tasks/priority/{priority}`    | Get tasks by priority        |
| **GET**   | `/tasks/project/{projectId}`    | Get tasks by project         |

### **Project Management APIs**

| Method  | Endpoint                         | Description                  |
|---------|----------------------------------|------------------------------|
| **POST**  | `/projects`                     | Create a new project         |
| **GET**   | `/projects`                     | Get all projects             |
| **GET**   | `/projects/{id}`                | Get project by ID            |
| **PUT**   | `/projects/{id}`                | Update project               |
| **DELETE**| `/projects/{id}`                | Delete project               |
| **GET**   | `/projects/user/{userId}`       | Get projects by user ID      |
| **GET**   | `/projects/status/{status}`     | Get projects by status       |
| **GET**   | `/projects/priority/{priority}` | Get projects by priority     |

### **User Management APIs**

| Method  | Endpoint                         | Description                  |
|---------|----------------------------------|------------------------------|
| **POST**  | `/users`                        | Create a new user            |
| **GET**   | `/users`                        | Get all users                |
| **GET**   | `/users/{id}`                   | Get user by ID               |
| **PUT**   | `/users/{id}`                   | Update user                  |
| **DELETE**| `/users/{id}`                   | Delete user                  |
| **GET**   | `/users/role/{role}`            | Get users by role            |
| **PATCH** | `/users/{id}/activate?status=X` | Activate or deactivate user  |

---

## 🛠 Example JSON Requests

### **Create Task**
```json
{
    "title": "Design UI",
    "status": "In Progress",
    "priority": "High",
    "deadline": "2025-04-01",
    "assignedTo": {
        "userId": 2,
        "username": "jane_smith",
        "email": "jane.smith@example.com",
        "role": "USER",
        "active": true
    }
}
```

### **Update Task Status**
```json
{
    "status": "Completed"
}
```

### **Response Example**
```json
{
   "title": "Implement Payment Gateway",
   "status": "Pending Review",
   "priority": "Medium",
   "deadline": "2025-08-15",
   "assignedTo": {
      "userId": 5,
      "username": "john_doe",
      "email": "john.doe@example.com",
      "role": "ADMIN",
      "active": false
   }
}
```
---
## Core Security Concepts

---

### 1. What is the difference between authentication and authorization?
- **Authentication** verifies *who* the user is (login).
- **Authorization** checks *what* the user is allowed to do (permissions, roles).

---

### 2. How does Spring Security handle the authentication flow for form login?
- Spring intercepts the login form POST request at `/login`, validates credentials via `AuthenticationManager`, and stores the result in `SecurityContextHolder`.

---

### 3. How is a stateless JWT flow different from session-based auth?
- **JWT** stores user data in a signed token sent with every request — no server memory used.
- **Session-based** stores user state on the server, typically using cookies.

---

### 4. How do filters in Spring Security get executed and ordered?
- Filters are executed in a specific **predefined order** within Spring Security’s `FilterChainProxy`.
- You can customize the order using `@Order` or `SecurityFilterChain`.

---

### 5. What role does the `SecurityContextHolder` play?
- It holds the **current user's security context**, including authentication and authorities.
- It's used throughout the app to retrieve user details securely.

---

### 6. Why should CSRF be disabled for JWT-based APIs?
- JWT APIs are **stateless** and do not use cookies, making CSRF protection unnecessary.
- CSRF is relevant for session-based apps where cookies are auto-included in requests.

---

### 7. What is MDC and how does it help with audit/log tracing?
- **Mapped Diagnostic Context (MDC)** stores contextual info like request ID or user ID per thread.
- It enhances logs by adding traceable metadata across logs in a request lifecycle.

---

### 8. What are the security risks of not validating a JWT token signature?
- Attackers can forge tokens and impersonate users if signature verification is skipped.
- Always validate JWT signatures to ensure authenticity and integrity.

---

### 9. How can you revoke or expire JWTs?
- Use a **short expiration time** and **refresh tokens**.
- For real-time revocation, maintain a token blacklist or store invalidated tokens in Redis.

---

### 10. What is the purpose of enabling or customizing CORS?
- CORS allows browsers to access APIs across different domains.
- Customizing it ensures only **trusted domains** can call your backend APIs.

---
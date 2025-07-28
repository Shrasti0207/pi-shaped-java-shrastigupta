# Observability Stack for Spring Boot Microservices - Day 5

Author: Shrasti Gupta  

## Stack Overview

This project sets up an **observability stack** for microservices using Docker Compose. It includes:

- **Elasticsearch**: Log storage and search engine
- **Logstash**: Log processing pipeline
- **Kibana**: Visualization for logs
- **Prometheus**: Metrics collection
- **Grafana**: Metrics dashboards
- **Zipkin**: Distributed tracing
- **Spring Cloud Sleuth**: Trace instrumentation for Spring Boot

---

## Core Observability Concepts

Understanding observability in microservices requires clear distinction between metrics, logs, and traces, and how tools like **Micrometer**, **Sleuth**, **Zipkin**, **Kibana**, and **Grafana** help you track and debug distributed systems. Below are core questions with practical insights and real-world use cases.

---

### 1. What are the differences between metrics, logs, and traces?
- **Metrics**: Numeric time-series data representing system health (e.g., CPU usage, request counts).
- **Logs**: Timestamped records of discrete events used for debugging or audit.
- **Traces**: End-to-end path of a single request across microservices, capturing performance and flow.

---

### 2. What’s the role of Micrometer and how does it enable backend-agnostic metrics?
- Micrometer is a facade that lets you collect application metrics in a unified way.
- It supports multiple monitoring systems (Prometheus, Datadog, New Relic) without changing your codebase.

---

### 3. How do traceId and spanId travel across microservice boundaries?
- Trace and span IDs are passed in HTTP headers like `X-B3-TraceId` and `X-B3-SpanId`.
- These IDs enable distributed tracing tools to connect logs and metrics across services.

---

### 4. How does Sleuth propagate context in WebClient/RestTemplate?
- Sleuth injects tracing headers into outbound HTTP calls made via WebClient or RestTemplate.
- This ensures trace continuity and consistent context sharing across services.

---

### 5. What is the difference between Timer, Gauge, and Counter in Micrometer?
- **Timer**: Records event count and total time (e.g., request duration).
- **Gauge**: Reflects the latest value (e.g., memory usage, queue size).
- **Counter**: Increments on every event (e.g., request count, error count).

---

### 6. What is MDC and how does it help with log correlation?
- **MDC (Mapped Diagnostic Context)** stores per-thread metadata (e.g., userId, traceId).
- It helps correlate logs across services by automatically adding context to each log message.

---

### 7. What is the difference between structured vs unstructured logging?
- **Structured logging** uses a consistent format (e.g., JSON), making it machine-parsable.
- **Unstructured logging** is plain text, human-readable, and harder to query programmatically.

---

### 8. How do you use Kibana to search for logs tied to a specific traceId?
1. Ensure logs are indexed with `traceId` (via MDC or Sleuth).
2. In Kibana Discover tab, search:
```java
traceId:"traceId-value"
```

### 9. How can you monitor memory, DB pool, and request error rates in Grafana?
- Use **Micrometer** to collect application metrics and **Prometheus** as the time-series database.
- Export metrics like `jvm.memory.used`, `hikaricp.connections.active`, and `http.server.requests` to visualize in Grafana dashboards with alerts.

---

### 10. How does sampling affect Zipkin trace accuracy and performance?
- **Sampling** controls what percentage of requests are traced to reduce overhead.
- Lower sampling improves performance but may miss issues; higher sampling gives better visibility at the cost of increased resource usage.

---


## 🐳 Docker Services

| Service       | Port  | Description                     |
|---------------|-------|---------------------------------|
| Elasticsearch | 9200  | Log storage                     |
| Kibana        | 5601  | UI for Elasticsearch logs       |
| Logstash      | 5000  | Parses and forwards logs        |
| Prometheus    | 9090  | Collects metrics                |
| Grafana       | 3000  | Visualizes Prometheus metrics   |
| Zipkin        | 9411  | Trace visualization             |

---

## Getting Started

### 1. Clone the Repository
```bash
git clone <repo-url>
cd Day5/observability
```

### 2. Start All Containers
```bash
docker-compose up -d
```

### 3. Verify Services
#### Elasticsearch
```bash
    http://localhost:9200
```

- Expected Output:
```bash
{
  "cluster_name": "docker-cluster",
  "tagline": "You Know, for Search"
}
```

#### Kibana
```bash
    http://localhost:5601
    Use it to view Elasticsearch logs.
```

#### Logstash

    Reads from logstash.conf and forwards to Elasticsearch.

#### Prometheus
```bash

    http://localhost:9090
    Go to Status > Targets to verify Spring Boot app is UP.
```

#### Grafana
```bash
    http://localhost:3000
```

- Default login:
```yaml
    Username: admin
    Password: admin
```

####  Zipkin
```bash
    http://localhost:9411
    Click Run Query to view traces.
```
---

## Grafana Dashboard Setup

- Open Grafana → http://localhost:3000
```bash

    Login with admin/admin

    Add Prometheus as a Data Source

        URL: http://prometheus:9090
```
---

## Tracing with Zipkin

Trigger a request to your service:
```bash

curl http://localhost:8080/api/hello

```

Then go to Zipkin → Click Run Query
➡️ You’ll see the trace with timing and microservice path.

---

## Tear Down
```bash

docker-compose down
```






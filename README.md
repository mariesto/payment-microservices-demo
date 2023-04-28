# Develop Payment Application with Microservices Architecture

### Architecture

High-Level Design
![image](./assets/payment-microservices-hld.png)

Low-Level Design (ERD)
![image](./assets/payment-microservices-erd.png)

---

### Functional Requirement

- payment-service
    - able to perform charge payment
    - able to perform refund
    - able to get latest payment status
- wallet-service
    - able to top-up the wallet balance
    - able to credit user wallet balance
    - able to debit user wallet balance
    - able to get list of user wallet transaction
- payment-provider-gateway-service
    - able to simulate payment flow to respective external payment provider
- order-service
    - CRUD order items
    - checkout order (payment)
    - cancel order (refund)
- user-service
    - CRUD
- API gateway-service
    - able to route the request accordingly
- registry-service
    - able to monitor all services

---

### Non-functional Requirement

- should be able to handle ACID from data POV
- should be able to perform retry in case payment is failed
- should be able to handle high volume of transactions with low latency
- should be able to handle scalability aspect
    - auto-scaling
    - load-balancing
- should be able to handle availability aspect
    - reduce single point of service (correlate with scalability)
    - add monitoring capability
        - use logging
        - use metrics (Prometheus)
        - use tracing (Jaeger)
        - health checks (API Gateway will collect all the health check status and export to Prometheus)

### Tech stack

| Name                  | Descriptions                       |
|-----------------------|------------------------------------|
| Spring boot + Java 11 | Framework and programming language |
| MySQL                 | Database                           |
| Kong                  | API Gateway                        |
| Consul                | Service Discovery                  |
| RabbitMQ              | Messaging Platform                 |
| Jaeger                | Tracing capability                 |
| Prometheus            | Monitoring                         |

### Service communication

We’re going to implement mixed communication type, which sync and async.

1. Sync communication will be done through HTTP only for :
   `payment-service` → `payment-gateway-service`
2. Async communication will be done through messaging (RabbitMQ) and using Pub/Sub pattern.


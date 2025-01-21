# LibraryHub 📚

A state-of-the-art library management system built with modern cloud-native microservices architecture using Spring Boot 3.4 and Spring Cloud 2024.

## Overview

LibraryHub is an enterprise-grade library management system that leverages cutting-edge technologies and best practices in software architecture. Built with a focus on scalability, resilience, and maintainability, it provides a comprehensive solution for modern libraries.

## Technical Architecture

### Microservices Architecture
The system is built using a modern microservices architecture with the following components:

#### Core Microservices
- **SharedData**: 
  - Shared library containing common data records
  - Reduces code redundancy across microservices
  - Centralizes data model definitions
  - Ensures consistency in data structures
  - Used by all microservices as a dependency

- **ms-books**: 
  - Manages book inventory and metadata
  - Uses PostgreSQL for persistent storage
  - Implements JPA for data access
  - Features comprehensive REST APIs for book management

- **ms-users**: 
  - Handles user management and authentication
  - Integrates with Keycloak for OAuth2/OpenID Connect
  - Manages user profiles and permissions

- **ms-borrows**: 
  - Manages book borrowing operations
  - Implements event-driven architecture using Kafka
  - Features circuit breakers for resilience

- **ms-reservations**: 
  - Handles book reservation system
  - Implements async processing
  - Uses optimistic locking for concurrent operations

- **ms-notifications**: 
  - Manages notification delivery
  - Supports email notifications through SMTP
  - Implements event-driven notification system

#### Infrastructure Services
- **Config Server**:
  - Centralized configuration management
  - Git-backed configuration storage
  - Environment-specific configurations

- **Discovery Server**:
  - Service registration and discovery
  - Load balancing
  - Health monitoring

- **API Gateway**:
  - Route management
  - Security
  - Rate limiting
  - Request/Response transformation

### Modern Technology Stack

#### Backend Technologies
- **Spring Boot 3.4**: Latest version with native compilation support
- **Spring Cloud 2024**: Modern cloud-native patterns
- **Spring Security**: OAuth2 resource server configuration
- **Spring Data JPA**: Modern data access
- **OpenFeign**: Declarative REST clients
- **Resilience4j**: Circuit breakers and fault tolerance
- **Lombok**: Reduces boilerplate code
- **SharedData Library**: Common data models and utilities

#### Data Layer
- **PostgreSQL**: Primary relational database
- **MongoDB**: Document storage for flexible data
- **Apache Kafka**: Event streaming platform
- **Redis**: Caching (planned)

#### Security
- **Keycloak 24.0.2**: Modern identity and access management
  - OAuth2/OpenID Connect support
  - Role-based access control
  - Single Sign-On capabilities

#### Monitoring & Operations
- **Spring Boot Actuator**: Health monitoring and metrics
- **Prometheus**: Metrics collection
- **Spring Boot Admin**: Application monitoring
- **Resilience4j**: Circuit breakers and retry mechanisms

#### Development Tools
- **Docker & Docker Compose**: Containerization
- **Maven**: Dependency management
- **Git**: Version control
- **PgAdmin**: Database management
- **Mongo Express**: MongoDB management
- **MailDev**: Email testing

## Features

- **Modern Authentication**: OAuth2/OpenID Connect with Keycloak
- **Event-Driven Architecture**: Using Apache Kafka for async operations
- **Circuit Breakers**: Resilience4j implementation for fault tolerance
- **Centralized Configuration**: Spring Cloud Config Server
- **Service Discovery**: Dynamic service registration and discovery
- **API Gateway**: Centralized routing and security
- **Monitoring**: Comprehensive health checks and metrics
- **Database Per Service**: Independent data storage per microservice
- **Shared Data Models**: Centralized data definitions via SharedData module
- **Containerization**: Docker support for all components

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/ImMrHak/LibraryHub.git
   ```

2. Start the infrastructure services:
   ```bash
   docker-compose up -d
   ```

### Available Services

#### Infrastructure (Docker)
- PostgreSQL: `localhost:5432`
- PgAdmin: `localhost:5050`
- MongoDB: `localhost:27017`
- Mongo Express: `localhost:8081`
- Kafka: `localhost:9092`
- MailDev: `localhost:1080` (UI), `localhost:1025` (SMTP)
- Keycloak: `localhost:6491`

#### Microservices
- Config Server: `8888`
- Discovery Server: `8761`
- API Gateway: `8762`
- Books Service: `8090`
- Users Service: `8091`
- Borrows Service: `8092`
- Reservations Service: `8093`
- Notifications Service: `8094`

## Development

### Prerequisites
- Java 17 or higher
- Docker and Docker Compose
- Maven 3.8+
- Git

### Building
```bash
mvn clean install
```

### Running Tests
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Authors

- Mohamed Hakkou
- Yassine Belouchi

## Acknowledgments

Special thanks to all contributors who have helped shape LibraryHub into a modern, cloud-native application.

# 📦 Product Inventory System

A professional full-stack application for real-time inventory management, built with a **Spring Boot** REST API, a **PostgreSQL** database, and a modern **Angular** standalone frontend.

---

## 🛠 Prerequisites

Ensure your environment meets these requirements before starting:
* **Docker & Docker Compose**: For containerized database management.
* **Java 17+**: Required to run the Spring Boot backend.
* **Node.js (v20+)**: Required for Angular development.
* **Angular CLI**: Install via `npm install -g @angular/cli`.

---

## 🚀 Quick Start Guide

To run the full system, it is recommended to open three separate terminal windows to monitor each service's logs.

### 1. Database (Docker)
We use Docker to ensure a consistent database environment without requiring a local PostgreSQL installation.

```bash
# From the project root (./product-inventory)
docker-compose up -d
```

### 2. Backend (Spring Boot)
The API layer handles business logic, security, and multipart file uploads for product images.

```bash
# 1. Navigate to the backend directory
cd inventory-backend

# 2. Start the service using the Maven Wrapper
./mvnw spring-boot:run
```

### 3. Frontend (Angular)
The user interface features reactive forms, dynamic filtering, and a responsive Bootstrap-based design.

```bash
# 1. Navigate to the frontend directory
cd inventory-frontend

# 2. Install dependencies (First time only)
npm install

# 3. Launch the development server
ng serve
```

### 4. Stopping the System
To stop the database and clean up Docker containers:

```bash
docker-compose down
```
# 📦 Product Inventory System

A professional full-stack application for real-time inventory management, built with a **Spring Boot** REST API, a **PostgreSQL** database, and a modern **Angular** standalone frontend.

---

## 🛠 Prerequisites

Ensure your environment meets these requirements before starting:
* **Docker & Docker Compose** (**Optional**): For containerized database management. You can download it at https://www.docker.com/.
* **Java 17+**: Required to run the Spring Boot backend. You can download it at https://www.java.com/en/download/.
* **Node.js (v24+)**: Required for Angular development. You can download it at https://nodejs.org/en/download.
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

## 1. Database (Without Docker)
We can also start this application without docker by connecting to the local PostgreSQL on your machine.
In the properties file you will need to replace the first 3 lines with the commented lines while also replacing your credentials.

```bash
# If you prefer to change the file with terminal use the nano command, or go and open the file in this path
nano inventory-backend/src/main/resources/application.properties
```

### 2. Backend (Spring Boot)
The API layer handles business logic, and multipart file uploads for product images.

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
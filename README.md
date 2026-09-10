# Basic CRUD API with Redis & PIT Mutation Testing

## 🚀 Overview
This repository contains a lightweight **CRUD API** designed for high-performance data management, utilizing **Redis** as a fast data base layer. To ensure maximum code reliability and high-quality test suites, the project integrates **PIT (PITest)** for mutation testing alongside standard unit tests.

---

## 🛠️ Tech Stack & Key Features
- **CRUD Operations:** Complete endpoints for Creating, Reading, Updating, and Deleting resources.
- **Robust Testing:** 
  - Standard unit tests verifying core business logic.
  - **PITest Mutation Testing** to analyze the quality of the tests by injecting faults (mutations) into the bytecode.

---

## 💻 How to Run the Project Locally

### 1. Prerequisites
Make sure you have installed:
- Your project's runtime environment (JDK 21)
- **Docker** (recommended for running Redis easily)

### 2. Start Redis
Run a Redis instance locally using Docker:
```bash
docker run -d --name redis-local -p 6379:6379 redis:alpine
````
Or with a redis installation
```bash
redis-server --daemonize yes
redis-cli
127.0.0.1:6379> ping
PONG
127.0.0.1:6379> exit
redis-cli monitor
```

### 3. Run the Application
*(Update this command based on your tech stack)*
```bash
.\gradlew bootRun
```

---

## 🧪 Testing & Code Quality

### Running Standard Unit Tests
To run the basic test suite and ensure all assertions pass:
```bash
.\gradlew test
```

### Running PIT Mutation Testing
To measure the true strength of your tests, execute the mutation coverage report:
```bash
.\gradlew pitest
```
*Once finished, you can find the detailed HTML report under `build/reports/pitest/index.html` to see which mutations survived and which were killed.*

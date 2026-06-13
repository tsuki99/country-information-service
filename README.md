# 🌍 Country Information Service

Spring Boot REST API that integrates with REST Countries API.

---

## 📌 Description

Country Information Service is a Spring Boot application that works with the external **REST Countries API**.

It allows users to:
- search countries by name
- retrieve detailed information about a specific country
- compare two countries by region, currency, population, and area

The project demonstrates working with external APIs, DTO mapping, clean architecture, and exception handling.

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Jackson (ObjectMapper)
- MapStruct
- Lombok
- Swagger / OpenAPI

---

## 🚀 Features

- Search countries by partial name
- Get detailed country information
- Compare two countries
  - same region
  - same currency
  - population comparison
  - area comparison
- Integration with external REST Countries API
- Global exception handling
- Swagger documentation

---

## 📡 API Endpoints

### 🔎 Search countries by partial name

```http
GET /countries/search-by-part-name?name={name}
```

---

### 🌍 Get country by full name

```http
GET /countries/search-by-full-name?name={name}
```

---

### ⚖️ Compare two countries

```http
GET /countries/compare?first={country}&second={country}
```

---

## 📖 Swagger UI

After starting the application, API documentation is available at:
http://localhost:8080/swagger-ui/index.html

---

## 🧠 How it works

- The application does NOT use a database
- All data is retrieved from external REST Countries API
- JSON responses are mapped into internal DTOs using Jackson + MapStruct
- Business logic processes and transforms external data into clean API responses

---

## 🏗 Architecture

- Controller layer — REST endpoints
- Service layer — business logic + external API communication
- Mapper layer — DTO transformations (MapStruct)
- Client layer — HTTP communication with external API
- Exception handling — global @RestControllerAdvice

---

## ⚠️ Error Handling

- `EntityNotFoundException` → returns 404 Not Found
- `RuntimeException` → returns 500 Internal Server Error (unexpected errors)

---

## ✨ Key Highlights

- External API integration (REST Countries API)
- Clean layered architecture
- DTO mapping with MapStruct
- Robust exception handling
- Swagger API documentation
- No database architecture (stateless service)

---

## ▶️ How to Run

1. Clone repository

`git clone <repo-url>`

2. Run application

Using Maven:

`mvn spring-boot:run`

Or run directly from IDE (IntelliJ IDEA)

3. Application will start on:

`http://localhost:8080`



# URL Shortener Service (Go + Gin)

A modular URL Shortening Service built with Go and the Gin framework. This project implements the Repository Pattern to ensure separation of concerns, making the codebase scalable, testable, and easy to maintain.

This project is based on the roadmap.sh [URL Shortening Service](https://roadmap.sh/projects/url-shortening-service) challenge.

## Features

- Shorten URLs: Generate unique short codes for long URLs.

- Redirection: efficient redirection from short code to original URL.

- Update/Delete: Manage existing shortened URLs (Update original destination or Delete entry).

- Statistics: Retrieve usage statistics for a specific short URL.

- Modular Architecture: Uses the Repository Pattern to abstract database operations.

- Local Database: Data is persisted in a local SQLite database file (no external database server required).


## Tech Stack

- Language: Go (Golang)

- Framework: Gin Gonic

- Database: SQLite, accessed through GORM

- Containerization: Docker & Docker Compose

- Architecture: Clean Architecture / Repository Pattern


## Project Structure

This project follows the Standard Go Project Layout to maintain clean code organization:

```bash
url-shortener/
├── cmd/
│   └── main.go                  # 🚀 Single entry point (DI wiring & server startup)
├── internal/
│   ├── domain/                      # Core business entities
│   │   └── url.go
│   ├── handler/                     # HTTP transport layer (Gin handlers)
│   │   ├── dto/
│   │   │   └── url_dto.go
│   │   ├── mapper/
│   │   │   └── url_mapper.go
│   │   └── url_handler.go
│   ├── repository/                  # Data access layer (GORM & DB setup)
│   │   ├── db.go
│   │   └── url_repository.go
│   └── service/                     # Business logic layer
│       └── url_service.go
├── .dockerignore
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── go.mod
└── go.sum
```
    

## Installation

1. Clone the repository:

```bash
git clone https://github.com/MrAndrey24/go-url-shortener.git
cd url-shortener
```

2. Install dependencies:

```bash
go mod download
```
3. Run the application:

```bash
go run main.go
```
Or

```bash
go run .
```

The server will start by default on port 8080.
The application creates or opens `data.db` in the current working directory and automatically migrates the URL schema on startup.


## Running with Docker

```bash
docker-compose up --build
```
## API Reference
Based on the [roadmap.sh](https://roadmap.sh/projects/url-shortening-service) specification, the following endpoints are available:


## Endpoints Summary

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/shorter` | Create a new short URL |
| `GET` | `/api/v1/shorter/:code` | Retrieve original URL by short code |
| `PUT` | `/api/v1/shorter/:code` | Update an existing short URL |
| `GET` | `/api/v1/shorter/:code/stats` | Retrieve click/visit statistics for a short URL |
| `DELETE` | `/api/v1/shorter/:code` | Delete a short URL |

---

## Endpoint Details

### 1. Create Short URL

Creates a new short code for a given long URL.

```http
POST /api/v1/shorter
```

#### Headers
| Header | Type | Description |
| :--- | :--- | :--- |
| `Content-Type` | `string` | **Required**. Must be `application/json` |

#### Request Body
```json
{
  "url": "https://www.example.com/very/long/url/path"
}
```

#### Response (`201 Created`)
```json
{
  "id": 1,
  "url": "https://www.example.com/very/long/url/path",
  "shortCode": "aB3k9X",
  "createdAt": "2026-09-11T09:24:41Z",
  "updatedAt": "2026-09-11T09:24:41Z"
}
```

---

### 2. Get Short URL

Retrieves the original URL and details corresponding to the provided short code.

```http
GET /api/v1/shorter/:code
```

#### Path Parameters
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `code` | `string` | **Required**. The unique short code of the URL |

#### Response (`200 OK`)
```json
{
  "id": 1,
  "url": "https://www.example.com/very/long/url/path",
  "shortCode": "aB3k9X",
  "createdAt": "2026-09-11T09:24:41Z",
  "updatedAt": "2026-09-11T09:24:41Z"
}
```

#### Response (`404 Not Found`)
```json
{
  "error": "Short code not found"
}
```

---

### 3. Update Short URL

Updates the destination URL associated with an existing short code.

```http
PUT /api/v1/shorter/:code
```

#### Path Parameters
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `code` | `string` | **Required**. The unique short code to update |

#### Headers
| Header | Type | Description |
| :--- | :--- | :--- |
| `Content-Type` | `string` | **Required**. Must be `application/json` |

#### Request Body
```json
{
  "url": "https://www.example.com/updated/new/destination"
}
```

#### Response (`200 OK`)
```json
{
  "id": 1,
  "url": "https://www.example.com/updated/new/destination",
  "shortCode": "aB3k9X",
  "createdAt": "2026-09-11T09:24:41Z",
  "updatedAt": "2026-09-11T09:24:50Z"
}
```

---

### 4. Get Short URL Stats

Retrieves access statistics and usage metrics for a short URL.

```http
GET /api/v1/shorter/:code/stats
```

#### Path Parameters
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `code` | `string` | **Required**. The unique short code to inspect |

#### Response (`200 OK`)
```json
{
  "id": 1,
  "url": "https://www.example.com/very/long/url/path",
  "shortCode": "aB3k9X",
  "accessCount": 42,
  "createdAt": "2026-09-11T09:24:41Z",
  "updatedAt": "2026-09-11T09:24:41Z"
}
```

---

### 5. Delete Short URL

Deletes a short URL mapping by its code.

```http
DELETE /api/v1/shorter/:code
```

#### Path Parameters
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `code` | `string` | **Required**. The unique short code to delete |

#### Response (`204 No Content`)
*(No response body)*


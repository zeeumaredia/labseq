# Labseq

Labseq is a full-stack implementation of the **Labseq sequence** using:

- **Backend**: Quarkus (Java 21)
- **Frontend**: Angular (standalone, Angular 17+)
- **Containerization**: Docker & Docker Compose

The application exposes a REST API to compute Labseq values and a web UI to interactively query and display results.

---

## Features

### Backend
- Quarkus REST API: `GET /labseq/{n}`
- Returns JSON:
  ```json
  { "n": <n>, "value": "<BigInteger>" }
  ```
- In-memory prefix cache for fast iterative computation
- Efficient for large inputs (tested at `n = 100000`)
- Input validation with proper HTTP status codes
- OpenAPI + Swagger UI enabled

### Frontend
- Angular standalone application
- Bootstrap-based responsive UI
- Input validation before API calls
- Displays only the computed value (no horizontal scrolling)
- API calls proxied to backend in dev and Docker environments

---

## Assumptions & Limits

- The Labseq sequence grows extremely fast.
- To prevent memory exhaustion:
  - Requests above `MAX_N` are rejected.
  - Current limit:
    ```
    MAX_N = 200000
    ```
- This safely covers the exercise requirement (`n = 100000`).

---

## API Contract

### `GET /labseq/{n}`

| Condition | HTTP Status |
|---------|------------|
| `n >= 0` | 200 OK |
| `n < 0` | 400 Bad Request |
| `n > MAX_N` | 413 Payload Too Large |

---

## OpenAPI / Swagger

- Swagger UI:
  ```
  http://localhost:8080/q/swagger-ui
  ```
---

## Run with Docker (Recommended)

### Prerequisites
- Docker
- Docker Compose

### Build and start the full stack
From the project root:

```bash
docker compose down -v
docker compose up --build
```

### Access points
- Frontend:
  ```
  http://localhost
  ```
- Backend API:
  ```
  http://localhost/labseq/{n}
  ```
- Swagger UI:
  ```
  http://localhost/q/swagger-ui
  ```

Docker Compose will:
- Build the Angular frontend
- Serve it via Nginx
- Route `/labseq/**` requests to the Quarkus backend

---

## Run without Docker (Development)

### Backend
```bash
cd backend
mvn quarkus:dev
```

Backend runs at:
```
http://localhost:8080
```

### Frontend
```bash
cd frontend
npm install
npm start
```

Frontend runs at:
```
http://localhost:4200
```

The Angular dev server uses a proxy configuration to forward API calls to the backend.

---

## Technical Notes
- No recursion is used for sequence calculation.
- Computation is iterative with caching for performance.
- `BigInteger` is used to avoid numeric overflow.
- Frontend displays only the sequence value for readability.

---

## Author

Zeeshan Zulfiqar

zeeumaredia@gmail.com
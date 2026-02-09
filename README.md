# Labseq

## Requirements implemented
- Quarkus (Java 21) REST API: `GET /labseq/{n}`
- Returns JSON: `{ "n": <n>, "value": "<BigInteger>" }`
- Uses an in-memory prefix cache to speed up intermediate calculations (values 0..n)
- OpenAPI + Swagger UI enabled
- Performance target: designed to compute `l(100000)` efficiently using iterative cached expansion

## Assumptions
- Although the input index is theoretically any non-negative integer, the value size and prefix caching make extremely large values impractical in memory.
- To keep the service stable, requests above `MAX_N` are rejected with HTTP 413.
    - Current `MAX_N = 200000` (covers the exercise performance target of `100000`).

## API
- `GET /labseq/{n}`
    - `n >= 0` -> 200
    - `n < 0` -> 400
    - `n > MAX_N` -> 413

## Swagger / OpenAPI
- Swagger UI: `/q/swagger-ui`
- OpenAPI spec: `/q/openapi`

## Run locally
```bash
cd backend
mvn quarkus:dev

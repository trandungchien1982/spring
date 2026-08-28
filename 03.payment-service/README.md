# Payment Service + OTel Java Agent + Tempo

Spring Boot 3.x / JDK 21 CRUD demo with zero-code OpenTelemetry Java Agent tracing.

## Agent prerequisite
Place `opentelemetry-javaagent.jar` in the project root before `docker compose up --build`.
The agent binary is intentionally not bundled in this source ZIP.

## Build
`gradle clean bootJar`

## Run locally
`java -javaagent:opentelemetry-javaagent.jar -Dotel.service.name=user-service -Dotel.traces.exporter=otlp -Dotel.exporter.otlp.endpoint=http://localhost:4318 -Dotel.exporter.otlp.protocol=http/protobuf -jar build/libs/user-service-1.0.0.jar`

## Docker
`docker compose up --build`

## CRUD examples
Create: `curl -X POST http://localhost:8080/api/users -H 'Content-Type: application/json' -d '{"username":"alice","email":"alice@example.com"}'`

Get: `curl http://localhost:8080/api/users/1`

List: `curl http://localhost:8080/api/users`

Update: `curl -X PUT http://localhost:8080/api/users/1 -H 'Content-Type: application/json' -d '{"username":"alice2","email":"alice2@example.com"}'`

Delete: `curl -X DELETE http://localhost:8080/api/users/1`

Tempo HTTP endpoint: `http://localhost:3200`.

Storage is in-memory; users are lost on app restart.

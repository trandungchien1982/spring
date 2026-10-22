# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository model

This repo is a collection of Spring Boot learning examples (README is in Vietnamese). **Each branch is a separate, self-contained example project** (e.g. `01.HelloWorld`, `19.SendEmail`, `25.BarCodes`, `26.RabbitMQ+Retry`). Branches do not share code with each other, so the content of `build.gradle`, `src/`, scripts and Docker files depends on the checked-out branch. Check the current branch before assuming what the app does.

On the current branch (`26.RabbitMQ+Retry`): Spring Boot 3.5.4 / Java 21 / Gradle app `rabbitmq-retry`. It demonstrates RabbitMQ consumer retry with a dead-letter queue, plus Unleash feature flags to toggle behaviour at runtime. Dependencies: `spring-boot-starter-amqp`, `-web`, `unleash-client-java` 12.3.1, Lombok.

Configuration lives in `application.properties`, and every value can be overridden by env vars:
- Server: `APP_PORT` (default **9191** locally; the Dockerfile/compose expose 9091).
- RabbitMQ: `RABBITMQ_HOST`, `RABBITMQ_PORT`, `RABBITMQ_USER`, `RABBITMQ_PWD`, `RABBITMQ_VHOST`. Queue names are `app.rabbitmq.main-queue=tdc-main-queue` and `app.rabbitmq.simulate-failed-queue` (`SIMULATE_FAILED_QUEUE`, default `tdc-simulate-failed-queue`).
- Unleash: `UNLEASH_API_URL`, `UNLEASH_API_TOKEN`, `UNLEASH_APP_NAME`, `UNLEASH_INSTANCE_ID`, `UNLEASH_FETCH_INTERVAL`.

## Commands

```bash
./gradlew build                  # compile + test + bootJar (build/libs/rabbitmq-retry-0.0.1-SNAPSHOT.jar)
./gradlew bootRun                # run the app (port 9191 by default, override with APP_PORT)
./gradlew test                   # all tests (JUnit Platform)
./gradlew test --tests com.example.demo.DemoApplicationTests   # single test class (append .method for one method)
```

There is no lint/format tooling configured.

Docker flow (numbered scripts, run in order): `02.build-apps.sh` (gradle build) → `03.build-docker.sh`

## Architecture

Package `com.example.demo`:
- `DemoApplication`: entry point. `main` prints a few env vars (`RABBITMQ_HOST`, `UNLEASH_API_URL`, `UNLEASH_API_TOKEN`) at startup, which is debug output that includes the token.
- `controller/RabbitMQController`: `GET /ok-message` sends a JSON `QueueMessage` to `tdc-main-queue`; `GET /failed-message` sends one to the simulate-failed queue. Both use `RabbitTemplate` on the default exchange (routing key = queue name).
- `model/QueueMessage`: Lombok DTO with `key` and `message`.
- `config/RabbitMQConfig`: declares the 3 durable queues (`tdc-main-queue`, `tdc-simulate-failed-queue`, and `tdc-simulate-failed-queue.dlq`) and the `Jackson2JsonMessageConverter` bean, which Boot wires into `RabbitTemplate`. It also defines `simulateFailedContainerFactory`, which carries the retry advice: stateless, `MAX_RETRY = 5` (6 attempts in total), exponential backoff 1s ×2 up to 10s. When retries are exhausted, `RepublishMessageRecoverer` publishes the message to the `.dlq` queue. Retry is attached to this factory only, not through `spring.rabbitmq.listener.simple.retry.*`, so other listeners are unaffected.
- `config/UnleashConfig`: `Unleash` bean (async initial fetch, polls toggles every `UNLEASH_FETCH_INTERVAL` seconds).
- `consumer/OkMessageConsumer`: listens on the main queue with the default factory. It skips the message (logs only) unless flag `ff-ok-message` (`app.feature-flags.ok-message`) is enabled.
- `consumer/SimulateFailedConsumer`: listens on the simulate-failed queue with `simulateFailedContainerFactory`. When flag `ff-simulate-error` is enabled (hardcoded in the class), it sleeps 3s and throws `IllegalStateException` to trigger the retry and DLQ path. Otherwise it succeeds.

Tests: only `DemoApplicationTests` (context load). It needs no broker only if the context can start without one, so check before relying on it.

## Docker

`Dockerfile` (liberica OpenJRE 21 alpine) copies `build/libs/rabbitmq-retry-0.0.1-SNAPSHOT.jar`, so run `./gradlew build` before `docker build`. `04.docker-compose.yml` runs image `tdchien1982/spring:rabbitmq-retry-1.0` on port 9091, attached to the external network `common-network`. RabbitMQ and Unleash are not part of the compose file and must be reachable separately.

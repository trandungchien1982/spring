# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository model

This repo is a collection of Spring Boot learning examples (README is in Vietnamese). **Each branch is a separate, self-contained example project** (e.g. `01.HelloWorld`, `19.SendEmail`, `25.BarCodes`, `26.RabbitMQ+Retry`). Branches do not share code with each other, so the content of `build.gradle`, `src/`, scripts and Docker files depends on the checked-out branch. Check the current branch before assuming what the app does.

On the current branch (`26.RabbitMQ+Retry`): Spring Boot 3.5.4 / Java 21 app `rabbitmq-retry`. `RabbitMQController` (`GET /ok-message`) sends a JSON `QueueMessage` to queue `tdc-main-queue` via `RabbitTemplate`; the queue and JSON converter are declared in `config/RabbitMQConfig`. Connection is configured by `spring.rabbitmq.*` in `application.properties` (env: `RABBITMQ_HOST`, `RABBITMQ_PORT`, `RABBITMQ_USER`, `RABBITMQ_PWD`, `RABBITMQ_VHOST`). Retry logic is not implemented yet.

## Commands

```bash
./gradlew build                  # compile + test + bootJar (build/libs/rabbitmq-retry-0.0.1-SNAPSHOT.jar)
./gradlew bootRun                # run the app (port 9091 by default)
./gradlew test                   # all tests (JUnit Platform)
./gradlew test --tests com.example.demo.DemoApplicationTests   # single test class (append .method for one method)
```

There is no lint/format tooling configured.

Docker flow (numbered scripts, run in order): `02.build-apps.sh` (gradle build) → `03.build-docker.sh`

## Architecture

- Package `com.example.demo`: `DemoApplication` (entry point) and `MainController` (single REST controller).

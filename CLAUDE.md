# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository model

This repo is a collection of Spring Boot learning examples (README is in Vietnamese). **Each branch is a separate, self-contained example project** (e.g. `01.HelloWorld`, `19.SendEmail`, `25.BarCodes`, `26.RabbitMQ+Retry`). Branches do not share code with each other, so the content of `build.gradle`, `src/`, scripts and Docker files depends on the checked-out branch. Check the current branch before assuming what the app does.

On the current branch (`26.RabbitMQ+Retry`): a Spring Boot 3.5.4 / Java 21 (Gradle toolchain) web app. 
The RabbitMQ + retry work has not been added yet (no RabbitMQ dependency in `build.gradle`).

## Commands

```bash
./gradlew build                  # compile + test + bootJar (build/libs/barcodes-0.0.1-SNAPSHOT.jar)
./gradlew bootRun                # run the app (port 9091 by default)
./gradlew test                   # all tests (JUnit Platform)
./gradlew test --tests com.example.demo.DemoApplicationTests   # single test class (append .method for one method)
```

There is no lint/format tooling configured.

Docker flow (numbered scripts, run in order): `02.build-apps.sh` (gradle build) → `03.build-docker.sh`

## Architecture

- Package `com.example.demo`: `DemoApplication` (entry point) and `MainController` (single REST controller).

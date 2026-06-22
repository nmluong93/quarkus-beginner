# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Quarkus 3.36.2 REST API study project using RESTEasy Classic (JAX-RS) with JSON-B serialization. Java 25, Maven build.

## Commands

```bash
# Dev mode with live reload (Dev UI at http://localhost:8080/q/dev/)
./mvnw quarkus:dev

# Build
./mvnw package

# Run tests (unit only — ITs are skipped by default)
./mvnw test

# Run a single test
./mvnw test -Dtest=BookResourceTest

# Run integration tests against packaged app
./mvnw verify -DskipITs=false

# Build native executable (requires GraalVM)
./mvnw package -Dnative

# Build native in container (no GraalVM required)
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

## Architecture

Single JAX-RS resource `BookResource` under `org.luongnm93.quarkus.starting`, mounted at `/api/books`.

**Test split:**
- `BookResourceTest` — `@QuarkusTest`: starts Quarkus in test mode, uses RestAssured against the live server on a random port.
- `BookResourceIT` — `@QuarkusIntegrationTest`: extends `BookResourceTest` and re-runs the same tests against the packaged JAR/native binary. Skipped by default (`skipITs=true`); enabled by the `native` Maven profile or `-DskipITs=false`.

`application.properties` is currently empty — configuration goes there using `quarkus.*` keys.
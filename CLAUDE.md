# CLAUDE.md — MongoDB CRUD Demo

## Mission
Maintain a small educational Order Management CRUD application using Spring Boot 3.x, Java 21, Gradle, MongoDB 7.0 and Spring Data MongoDB.

## Architecture
- `model/`: MongoDB `@Document` Java records.
- `repository/`: interfaces extend `MongoRepository<T,String>`; derived and `@Query` methods.
- `repository/*FilterRepositoryImpl`: custom repository fragments implemented with `MongoTemplate`, building optional Criteria.
- `controller/`: REST CRUD endpoints; repository usage shown directly for clarity.
- `config/IndexesConfig`: compound indexes for common search predicates.
- `application.yml`: `MONGODB_URI` configuration.

## Project rules
1. Use **Spring Data MongoDB**, never Spring Data JPA annotations or Hibernate for these documents.
2. Keep Java version 21 and Spring Boot 3.5.x unless explicitly asked to upgrade.
3. Use Gradle (Groovy DSL). Do not add Maven build files.
4. Keep all fields `customerId`, `productId`, `version`, and `status` consistently mapped to MongoDB BSON field names.
5. Demo `version` is a business schema/version value, not `@Version` optimistic locking.
6. Prefer `MongoRepository` for CRUD and fixed queries, `MongoTemplate` for dynamic filters.
7. Preserve Decimal128 mapping for monetary `BigDecimal` fields.
8. Do not commit passwords, authentication URLs containing credentials, secrets, or local `.env` files.
9. Add tests for CRUD, query methods and filter combinations when extending behavior.
10. Production enhancements: pagination, authentication, authorization, optimistic concurrency, atomic inventory updates and integration tests with Testcontainers.

## Commands
- `docker compose up -d mongodb`
- `gradle bootRun`
- `gradle test`

## API examples
See `README.md`. Use curl or Bruno to test endpoints.

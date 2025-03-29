# CLAUDE.md - Guide for working with piksa-1

## Build/Run Commands
- Build: `./mvnw package`
- Dev mode: `./mvnw quarkus:dev`
- Run tests: `./mvnw test`
- Run single test: `./mvnw test -Dtest=GreetingResourceTest`
- Run integration tests: `./mvnw verify -DskipITs=false`
- Package as uber-jar: `./mvnw package -Dquarkus.package.jar.type=uber-jar`
- Build native executable: `./mvnw package -Dnative`

## Code Style Guidelines
- Package structure: boundary/control/impl (hexagonal architecture pattern)
- Imports: Organize alphabetically, java.* before jakarta.* before other imports
- Prefer interfaces with implementation classes
- Validation: Use Objects.requireNonNull for mandatory parameters
- Naming: Use descriptive names, interfaces without prefix, impl classes with "Impl" suffix
- Records: Use for immutable data objects when appropriate
- DI: Use constructor injection with final fields
- Error handling: Fail fast with proper validation, use System.Logger for logging
- Tests: Use QuarkusTest for unit tests, QuarkusIntegrationTest for integration tests
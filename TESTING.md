# Unit Test Conventions

## Naming

- Test class: `<ClassName>Test` (e.g., `BookResourceTest`)
- Test method: `should<ExpectedBehavior>` (e.g., `shouldGetAllBooks`)
- Class and methods are package-private — JUnit 5 does not require `public`

## Structure

Use RestAssured BDD style. `given()`, `.when()`, `.then()` sit at the first indentation level; their chained calls are nested one level deeper:

```java
given()
        .header("Accept", "application/json")
.when()
        .get("/api/books")
.then()
        .statusCode(200)
        .body("size()", is(4));
```

## Imports

Order: third-party imports → static imports. No unused imports.

```java
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
```

## Annotations

- `@QuarkusTest` on the class — starts Quarkus in test mode against a random port
- `@Test` on each test method

## Test Split

| Class | Annotation | Runs against |
|---|---|---|
| `BookResourceTest` | `@QuarkusTest` | Live server in test mode |
| `BookResourceIT` | `@QuarkusIntegrationTest` | Packaged JAR / native binary |

`BookResourceIT` extends `BookResourceTest` and re-runs the same tests. It is skipped by default; enable with `-DskipITs=false` or the `native` Maven profile.

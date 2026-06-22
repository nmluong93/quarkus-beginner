package org.luongnm93.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BookResourceTest {
    @Test
    void shouldGetAllBooks() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/books")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(4));
    }

    @Test
    void shouldGetBookById() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/books/1")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is(1))
                .body("title", is("Understanding Quarkus"))
                .body("author", is("Antonio"));
    }

    @Test
    void shouldCountAllBooks() {
        given()
                .accept(ContentType.TEXT)
        .when()
                .get("/api/books/count")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("4"));
    }

}
package edu.san.boundary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class GreetingResourceTest {

  @Inject
  Logger log;

  @Test
  void testHelloEndpoint() {
    log.log(Level.INFO, "Starting tests...");

    given()
        .when().get("/hello")
        .then()
        .statusCode(200)
        .body(is("[1, kgrzanek@san.edu.pl]"));

    // "[1]"

    log.log(Level.INFO, "It's all good!");
  }

}
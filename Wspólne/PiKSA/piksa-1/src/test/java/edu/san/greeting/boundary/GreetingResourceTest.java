package edu.san.greeting.boundary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class GreetingResourceTest {

  static final Logger LOG = System
      .getLogger(GreetingResourceTest.class.getName());

  @Test
  void testHelloEndpoint() {
    LOG.log(Level.INFO, "testing GreetingResource::handlePost /greeting");

    given()
        .contentType(ContentType.JSON)
        .body("{\"whoToGreet\": \"ME\"}")
        .when().post("/greeting")
        .then()
        .statusCode(200)
        .body(is("{\"greetingMessage\":\"Hello ME\"}"));
  }

}
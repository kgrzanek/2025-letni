package edu.san.greeting.boundary;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.junit.jupiter.api.Test;

import edu.san.greeting.control.GreetingController;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class GreetingResourceTest {

  static final Logger LOG = System.getLogger(GreetingResourceTest.class.getName());

  @Test
  void testHello() {
    final var greetingControler = new GreetingController();
    final var greetingResource = new GreetingResource(greetingControler);
    try (var result = greetingResource.handlePost(new GreetingQueryImpl("YOU"))) {
      assertThat(result.getStatus()).isEqualTo(200);
    }
  }

  @Test
  void testHelloEndpoint() {
    LOG.log(Level.INFO, "Testujemy end-point /greeting");

    given()
        .contentType(ContentType.JSON)
        .body("{\"whoToGreet\": \"ME\"}")
        .when().post("/greeting")
        .then()
        .statusCode(200)
        .body(is("{\"greetingMessage\":\"Hello ME\"}"));
  }

}
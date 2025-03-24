package edu.san.greeting.boundary;

import java.util.Objects;

public record GreetingResponse (String greetingMessage) {

  public GreetingResponse {
    Objects.requireNonNull(greetingMessage);
  }

}

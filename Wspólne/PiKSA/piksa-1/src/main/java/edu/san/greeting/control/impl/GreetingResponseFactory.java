package edu.san.greeting.control.impl;

import java.util.Objects;

import edu.san.greeting.control.GreetingResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingResponseFactory implements GreetingResponse.Factory {

  @RegisterForReflection
  public static record GreetingResponseImpl(String greetingMessage)
      implements GreetingResponse {

    public GreetingResponseImpl {
      Objects.requireNonNull(greetingMessage);
    }
  }

  @Override
  public GreetingResponse createGreetingResponse(String greetingMessage) {
    return new GreetingResponseImpl(greetingMessage);
  }

}

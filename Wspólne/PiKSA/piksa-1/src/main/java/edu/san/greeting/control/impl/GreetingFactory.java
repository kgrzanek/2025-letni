package edu.san.greeting.control.impl;

import java.util.Objects;

import edu.san.greeting.control.GreetingResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingFactory implements GreetingResponse.Factory {

  @RegisterForReflection
  public record GreetingResponseImpl(String greetingMessage)
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

// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.greeting.control;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingController {

  private final GreetingResponse.Factory greetingResponseFactory;

  GreetingController(GreetingResponse.Factory greetingResponseFactory) {
    this.greetingResponseFactory = greetingResponseFactory;
  }

  public GreetingResponse sayHello(GreetingQuery greetingQuery) {
    return greetingResponseFactory
        .createGreetingResponse("Hello " + greetingQuery.whoToGreet());
  }

}

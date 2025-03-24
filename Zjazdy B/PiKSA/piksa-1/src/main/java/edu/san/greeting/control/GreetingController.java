// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.greeting.control;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingController {

  public String sayHello(GreetingQuery greetingQuery) {
    return "Hello " + greetingQuery.whoToGreet();
  }

}

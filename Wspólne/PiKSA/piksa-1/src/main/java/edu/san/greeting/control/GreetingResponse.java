package edu.san.greeting.control;

public interface GreetingResponse {

  String greetingMessage();

  interface Factory {

    GreetingResponse createGreetingResponse(String greetingMessage);

  }

}

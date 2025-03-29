// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.greeting.boundary;

import edu.san.greeting.control.GreetingQuery;

public class GreetingQueryRequest implements GreetingQuery {

  private String whoToGreet;

  public GreetingQueryRequest() {}

  public GreetingQueryRequest(String whoToGreet) {
    this.whoToGreet = whoToGreet;
  }

  public void setWhoToGreet(String whoToGreet) {
    this.whoToGreet = whoToGreet;
  }

  @Override
  public String whoToGreet() {
    return whoToGreet;
  }

}

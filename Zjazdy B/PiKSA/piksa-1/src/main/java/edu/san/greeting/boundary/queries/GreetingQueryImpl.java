// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.greeting.boundary.queries;

public class GreetingQueryImpl implements GreetingQuery {

  private String whoToGreet;

  public GreetingQueryImpl() {}

  public GreetingQueryImpl(String whoToGreet) {
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

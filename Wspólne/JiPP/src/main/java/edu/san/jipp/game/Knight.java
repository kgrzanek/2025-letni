package edu.san.jipp.game;

public class Knight<T> {

  public Sack<T> sack = new Sack<>();

  public void handleTreasure(T treasure) {
    if(sack.put(treasure)) {
      System.out.println("I got a " + treasure);
    } else {
      System.out.println("I have to skip " + treasure);
    }
  }

}

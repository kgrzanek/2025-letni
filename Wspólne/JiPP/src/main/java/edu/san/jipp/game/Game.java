package edu.san.jipp.game;

public class Game {

  public static void main(String[] args) {
    // Faza 1
    Knight<Integer> knight1 = new Knight<>();
    Knight<String> knight2 = new Knight<>();

    knight2.handleTreasure("Gold");
    knight2.handleTreasure("Sword");
    knight1.handleTreasure(10_000_000);
    knight1.handleTreasure(10_000);
    knight2.handleTreasure("Medicine");

    // Faza 2
    int total = knight1.sack.treasure1 + knight1.sack.treasure2;

    String total2 = knight2.sack.treasure1 + knight2.sack.treasure2;
    System.out.println("Total = " + total);

    System.out.println("Total = " + total2);
  }

}

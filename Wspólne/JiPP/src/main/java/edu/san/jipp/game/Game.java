package edu.san.jipp.game;

public class Game {

  public static void main(String[] args) {
    // Faza 1
    final var knight1 = new Knight<Integer>();
    final var knight2 = new Knight<String>();

    knight2.handleTreasure("Gold");
    knight2.handleTreasure("Sword");
    knight1.handleTreasure(10_000_000);
    knight1.handleTreasure(10_000);
    knight2.handleTreasure("Medicine");

    // Faza 2
    final var total = knight1.sack.treasure1 + knight1.sack.treasure2;

    final var total2 = knight2.sack.treasure1 + knight2.sack.treasure2;
    System.out.println("Total = " + total);

    System.out.println("Total = " + total2);
  }

}

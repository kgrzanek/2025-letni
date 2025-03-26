package edu.san.jipp;

public class Program01 {

  public static void main(String[] args) {
    // Typy proste:

    // 8b 16b 32b 64b 32b 64b
    // byte <: short <: int <: long <: float <: double
    // char <: int <: long <: float <: double

    // boolean {true, false}
    // void

    final byte b = 23;
    int i = b;
    System.out.println(i);

    final var n = 1023;
    final var b1 = (byte) n;

    // double y = n;

    System.out.println(b1);
    System.out.println("Hello World!");
  }

}

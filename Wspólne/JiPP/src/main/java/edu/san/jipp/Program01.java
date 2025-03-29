package edu.san.jipp;

public class Program01 {

  public static void main(String[] args) {
    // Typy proste:

    // 8b 16b 32b 64b 32b 64b
    // byte <: short <: int <: long <: float <: double
    // char <: int <: long <: float <: double

    // boolean {true, false}
    // void

    // X : REAL := 0.356;
    // byte < short

    final byte b = 127;
    int i = b;
    System.out.println(i);

    final var n = 1023;
    final byte b1 = (byte) n;

    // double y = n;

    var f = 2.26f;

    double d1 = 2.26 +  7;
    //    <d>   <d>  + <int>

    System.out.println(d1);
    System.out.println(f);
    System.out.println("b1=" + b1);
    System.out.println("Hello World!");
  }

}

package edu.san.jipp;

public class Program03 {

  static double foo(double x) {
    // Q: Czy to jest polimorficzna procedura?
    // A: Tak i Nie - jest to tzw. polimorfizm ad-hoc.
    return 2 * x + 3;
  }

  static int foo(int n) {
    return 3 * n + 4;
  }

  public static void main(String[] args) {
    final var d = 4.56;
    final var n = 7;

    System.out.println(foo(d));
    System.out.println(foo(n));
  }

}

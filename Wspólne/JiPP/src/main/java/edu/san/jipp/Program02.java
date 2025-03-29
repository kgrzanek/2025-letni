package edu.san.jipp;

public class Program02 {

  static double foo(double x) {
    return 2 * x + 3;
  }

  static int foo(int j) {
    return 4 * j - 7;
  }

  public static void main(String... args) {
    // POLIMORFIZM

    final var y = 2.25;
    final var n = 2;

    System.out.println(foo(y));
    System.out.println(foo(n));

    // Q: Czy foo jest polimorficzna?
    // A: Nie jest PRAWDZIWIE polimorficzna.
    // Komentarz: W drugim przypadku mamy do czynienia z
    // nejawną konwersją (ang. coercion) int n na double x.
  }

}

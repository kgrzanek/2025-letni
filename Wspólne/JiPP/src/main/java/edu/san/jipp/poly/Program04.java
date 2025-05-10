package edu.san.jipp.poly;

public class Program04 {

  static void say10Times(A a) {
    // Ta procedura jest prawdziwie polimorficzna i występuje w niej polimorfizm
    // inkluzyjny.
    for (var i = 0; i < 10; i++) {
      a.saySomething();
      // Występuje tutaj tzw. późne wiązanie (ang. late binding).
      // * Wiązanie: proces określania wartości odpowiadającej danemu symbolowi
      // * Późne: Odbywa się dopiero na etapie działania programu (w runtimie)
      // * Gdyby było wczesne, znaczyłoby to, że ma miejsce w trakcie
      // kompilacji.
    }
  }

  public static void main(String[] args) {
    final var a1 = new A();
    final var b1 = new B();

    say10Times(a1);
    say10Times(b1);
  }

}

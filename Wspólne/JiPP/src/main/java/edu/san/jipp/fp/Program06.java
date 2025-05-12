// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.fp;

public class Program06 {

  public static void main(String... args) {

    SomeInterface someObject = () -> System.out
        .println("Działa someObject::someMethod()");

    someObject.someMethod();
    System.out.println(someObject.getClass());

    // Wyrażenia jak to: ( ... ) -> { ... } nazywamy lambda-wyrażeniami. Ich
    // wartością są anonimowe funkcje (procedury), inaczej - lambdy.

    // Programowanie funkcyjne to STYL PROGRAMOWANIA. Polega on na eliminacji
    // zmiennych (tzn. symboli, których wartości można modyfikować przy pomocy
    // operatora przypisania). Zamiast tego stosuje się:
    // * if
    // * rekurencję
    // * lambdy
    // * symbole definiowane w kodzie programu wraz z ich wartościami

  }

}

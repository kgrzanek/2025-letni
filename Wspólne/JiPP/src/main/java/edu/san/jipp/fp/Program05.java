// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.fp;

public class Program05 {

//  static class SomeClass implements SomeInterface {
//    @Override
//    public void someMethod() {
//      System.out.println("Działa SomeClass::someMethod()");
//    }
//  }

  public static void main(String... args) {
//    var someObject = new SomeClass();
//    someObject.someMethod();

    var someObject = new SomeInterface() {
      @Override
      public void someMethod() {
        System.out.println("Działa someObject::someMethod()");
      }
    };
    someObject.someMethod();
    System.out.println(someObject.getClass());

    // Klasa Program05$1 to jest anonimowa klasa wewnętrzna implementująca
    // interfejs SomeInterface.

  }

}

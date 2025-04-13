package edu.san.jipp.game;

public class Sack<T> { // T - zmienna typowa (ang. type-variable)

  public T treasure1;

  public T treasure2;

  public T treasure3;

  public boolean put(T treasure) {
    if (treasure1 == null) {
      treasure1 = treasure;
      return true;
    }
    if (treasure2 == null) {
      treasure2 = treasure;
      return true;
    }
    if (treasure3 == null) {
      treasure3 = treasure;
      return true;
    }
    return false;
  }

}

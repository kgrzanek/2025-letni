// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Objects;

public final class O {

  public static <T> T nn(T obj) {
    return Objects.requireNonNull(obj);
  }

  public static <T> T nn(T obj, String message) {
    return Objects.requireNonNull(obj, message);
  }

  private O() {}

}

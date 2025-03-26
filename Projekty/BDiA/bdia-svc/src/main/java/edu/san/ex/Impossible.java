package edu.san.ex;

import java.util.Objects;
import java.util.function.Supplier;

public final class Impossible extends RuntimeException {

  public static Supplier<Impossible> raise(String message) {
    return () -> new Impossible(message);
  }

  public static Supplier<Impossible> raise(Supplier<String> messageSupplier) {
    Objects.requireNonNull(messageSupplier);
    return () -> new Impossible(messageSupplier.get());
  }

  public Impossible() {}

  public Impossible(String message) {
    super(message);
  }

  public Impossible(Throwable cause) {
    super(cause);
  }

  public Impossible(String message, Throwable cause) {
    super(message, cause);
  }

}

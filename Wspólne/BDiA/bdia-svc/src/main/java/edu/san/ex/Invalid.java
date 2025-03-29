package edu.san.ex;

import java.util.Objects;
import java.util.function.Supplier;

public final class Invalid extends RuntimeException {

  public static Supplier<Invalid> raise(String message, Object what) {
    return () -> new Invalid(message, what);
  }

  public static Supplier<Invalid> raise(
      Supplier<String> messageSupplier,
      Object what) {
    Objects.requireNonNull(messageSupplier);
    return () -> new Invalid(messageSupplier.get(), what);
  }

  public Invalid(Throwable cause, Object what) {
    this(null, cause, what);
  }

  public Invalid(String message, Object what) {
    this(message, null, what);
  }

  public Invalid(String message, Throwable cause, Object what) {
    super(message, cause, false, false);
    this.what = what;
  }

  @Override
  public String toString() {
    return "Invalid [what=" + what + ", getMessage()=" + getMessage() + "]";
  }

  public Object getWhat() {
    return what;
  }

  private final transient Object what;

}

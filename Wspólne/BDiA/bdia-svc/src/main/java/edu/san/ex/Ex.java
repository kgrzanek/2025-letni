package edu.san.ex;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public final class Ex {

  public static <T> T evalUnchecked(Callable<T> body) {
    try {
      return body.call();
    } catch (final Exception e) {
      return raise(e);
    }
  }

  public static <T> T raise(Throwable t) {
    sneakyThrow0(t);
    return null;
  }

  public static Supplier<RuntimeException> raise(String message) {
    return () -> new RuntimeException(message);
  }

  public static Supplier<RuntimeException> raise(
      Supplier<String> messageSupplier) {
    Objects.requireNonNull(messageSupplier);
    return () -> new RuntimeException(messageSupplier.get());
  }

  @SuppressWarnings("unchecked")
  private static <X extends Throwable> void sneakyThrow0(Throwable t) throws X {
    throw (X) t;
  }

  private Ex() {}

}

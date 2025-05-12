// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.fp;

import java.util.Objects;

import edu.san.jipp.fp.functions.Nullary;

public final class Delay<T> {

  public static <S> Delay<S> of(Nullary<S> provider) {
    return new Delay<>(provider);
  }

  public T value() {
    if (isPending()) {
      value = provider.call();
      setNonPending();
    }
    return value;
  }

  public boolean isPending() {
    return null != provider;
  }

  private void setNonPending() {
    provider = null;
  }

  private Nullary<T> provider;

  private T value;

  private Delay(Nullary<T> provider) {
    this.provider = Objects.requireNonNull(provider);
  }

}


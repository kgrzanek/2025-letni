package edu.san.jipp.seqs.impl;

import edu.san.jipp.seqs.ISeq;

public final class Nil<T> implements ISeq<T> {

  public static final Nil<Object> INSTANCE = new Nil<>();

  private Nil() {}

  @Override
  public T first() {
    throw new NullPointerException();
  }

  @Override
  public ISeq<T> rest() {
    return this;
  }

  @Override
  public ISeq<T> cons(T obj) {
    return new LinkedSeq<>(obj, this);
  }

  @Override
  public boolean isNil() {
    return true;
  }
}

package edu.san.jipp.seqs.impl;

import edu.san.jipp.seqs.ISeq;

public final class LinkedSeq<T> implements ISeq<T> {

  private final T first;

  private final ISeq<T> rest;

  LinkedSeq(T first, ISeq<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public T first() {
    return first;
  }

  @Override
  public ISeq<T> rest() {
    return rest;
  }

  @Override
  public ISeq<T> cons(T obj) {
    return new LinkedSeq<>(obj, this);
  }

}

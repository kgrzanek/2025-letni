package edu.san.jipp.seqs;

import edu.san.jipp.seqs.impl.Nil;

public interface ISeq<T> {
  // T - zmienna typowa (ang. type-variable), inaczej: parametr typowy

  @SuppressWarnings("unchecked")
  static <T> ISeq<T> nil() {
    return (ISeq<T>) Nil.INSTANCE;
  }

  T first();

  ISeq<T> rest();

  ISeq<T> cons(T obj);

}

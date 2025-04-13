package edu.san.jipp.seqs;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.san.jipp.refs.Ref;
import edu.san.jipp.seqs.impl.Nil;

public interface ISeq<T> extends Iterable<T> {
  // T - zmienna typowa (ang. type-variable), inaczej: parametr typowy

  @SuppressWarnings("unchecked")
  static <T> ISeq<T> nil() {
    return (ISeq<T>) Nil.INSTANCE;
  }

  T first();

  ISeq<T> rest();

  ISeq<T> cons(T obj);

  @Override
  default Iterator<T> iterator() {
    final var state = new Ref<ISeq<T>>(ISeq.this);
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return state.value != Nil.INSTANCE;
      }

      @Override
      public T next() {
        if (!hasNext())
          throw new NoSuchElementException();
        T value = ISeq.this.first();
        state.value = ISeq.this.rest();
        return value;
      }
    };
  }

}

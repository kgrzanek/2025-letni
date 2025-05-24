// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.seqs;

import edu.san.jipp.fp.functions.Unary;
import edu.san.jipp.seqs.impl.LazySeq;

public final class Seqs {

  // iterate(f, start) => [start, f(start), f(f(start)), f(f(f(start))), ... ]
  public static <T> ISeq<T> iterate(Unary<T, T> f, T start) {
    return LazySeq.of(start, () -> iterate(f, f.call(start)));
  }

  // map(f, [e1, e2, e3, ...]) => [f(e1), f(e2), f(e3), ...]
  public static <S, T> ISeq<T> map(Unary<S, T> f, ISeq<S> seq) {
    if (seq.isNil())
      return ISeq.nil();

    return LazySeq.of(f.call(seq.first()), () -> map(f, seq.rest()));
  }

  private Seqs() {}

}

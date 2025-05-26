// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.seqs;

import edu.san.jipp.fp.functions.Binary;
import edu.san.jipp.fp.functions.Pred;
import edu.san.jipp.fp.functions.Unary;
import edu.san.jipp.seqs.impl.LazySeq;

public final class Seqs {

  // map(f, [e1, e2, e3, ...]) => [f(e1), f(e2), f(e3), ...]
  public static <S, T> ISeq<T> map(Unary<S, T> f, ISeq<S> seq) {
    if (seq.isNil())
      return ISeq.nil();

    return LazySeq.of(f.call(seq.first()), () -> map(f, seq.rest()));
  }

  public static <S, T> T reduce(Binary<T, S, T> f, T accum, ISeq<S> seq) {
    if (seq.isNil())
      return accum;

    return reduce(f, f.call(accum, seq.first()), seq.rest());
  }

  // iterate(f, start) => [start, f(start), f(f(start)), f(f(f(start))), ... ]
  public static <T> ISeq<T> iterate(Unary<T, T> f, T start) {
    return LazySeq.of(start, () -> iterate(f, f.call(start)));
  }

  public static <T> ISeq<T> take(int n, ISeq<T> seq) {
    if (n == 0 || seq.isNil())
      return ISeq.nil();

    return LazySeq.of(seq.first(), () -> take(n - 1, seq.rest()));
  }

  public static <T> ISeq<T> filter(Pred<T> pred, ISeq<T> seq) {
    if (seq.isNil())
      return ISeq.nil();

    final var e = seq.first();
    if (pred.call(e))
      return LazySeq.of(e, () -> filter(pred, seq.rest()));

    return filter(pred, seq.rest());
  }

  public static <T> String asString(ISeq<T> seq) {
    if (seq.isNil())
      return "()";

    StringBuilder buf = new StringBuilder("(");
    var s = seq;
    for (;;) {
      buf.append(s.first());
      s = s.rest();
      if (s.isNil()) {
        break;
      }
      buf.append(",");
    }
    return buf.append(")").toString();
  }

  private Seqs() {}

}

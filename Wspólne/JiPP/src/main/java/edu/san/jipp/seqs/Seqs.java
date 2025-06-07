// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.seqs;

import edu.san.jipp.fp.functions.Binary;
import edu.san.jipp.fp.functions.UnaryPred;
import edu.san.jipp.fp.functions.Unary;
import edu.san.jipp.seqs.impl.LazySeq;

public final class Seqs {

  // iterate(f, start) => [start, f(start), f(f(start)), f(f(f(start))), ... ]
  public static <T> ISeq<T> iterate(Unary<T, T> f, T start) {
    return LazySeq.of(start, () -> iterate(f, f.call(start)));
  }

  // f : S -> T
  // seq : [S]
  // @returns [T]
  // map(f, [e1, e2, e3, ...]) => [f(e1), f(e2), f(e3), ...]
  public static <S, T> ISeq<T> map(Unary<S, T> f, ISeq<S> seq) {
    if (seq.isNil())
      return ISeq.nil();

    var e1 = seq.first();
    var fe1 = f.call(e1);

    return LazySeq.of(fe1, () -> map(f, seq.rest()));
  }

  // np. seq = [1, 2, 3, 4]
  // accum = 0
  // f = +

  // f accum seq
  // reduce(+, 0, [1, 2, 3, 4]) =>
  // reduce(+, +(0, 1), [2, 3, 4]) =>
  // reduce(+, 1, [2, 3, 4]) =>
  // reduce(+, +(1, 2), [3, 4]) =>
  // reduce(+, 3, [3, 4]) =>
  // reduce(+, +(3, 3), [4]) =>
  // reduce(+, 6, [4]) =>
  // reduce(+, +(6, 4), [ ]) =>
  // reduce(+, 10, [ ]) =>
  // 10
  public static <S, T> T reduce(Binary<T, S, T> f, T accum, ISeq<S> seq) {
    for (; !seq.isNil(); seq = seq.rest()) {
      accum = f.call(accum, seq.first());
    }
    return accum;
  }

  public static <T> ISeq<T> take(int n, ISeq<T> seq) {
    if (n == 0 || seq.isNil())
      return ISeq.nil();

    return LazySeq.of(seq.first(), () -> take(n - 1, seq.rest()));
  }

  public static <T> ISeq<T> filter(UnaryPred<T> pred, ISeq<T> seq) {
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
    var sep = "";
    for (var s = seq; !s.isNil(); s = s.rest(), sep = ",") {
      buf.append(sep);
      buf.append(s.first());
    }
    return buf.append(")").toString();
  }

  private Seqs() {}

}

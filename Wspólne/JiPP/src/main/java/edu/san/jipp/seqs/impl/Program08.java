// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.seqs.impl;

import edu.san.jipp.seqs.Seqs;

public class Program08 {

  public static void main(String... args) {
    // Unary<Long, Long> inc = n -> n + 1L;

    final var s1 = Seqs.iterate(n -> n + 1L, 0L);
    final var s2 = Seqs.map(n -> n * n, s1);
    final var s3 = Seqs.filter(n -> n % 2L == 0L, s2);
    final var s4 = Seqs.take(10, s3);

    final var n = Seqs.reduce((i, j) -> i + j, 0L, s4);

    System.out.println(Seqs.asString(s4));
    System.out.println(n);

//    System.out.println(Nsquared.first());
//    System.out.println(Nsquared.rest().first());
//    System.out.println(Nsquared.rest().rest().first());
//    System.out.println(Nsquared.rest().rest().rest().first());
  }

}

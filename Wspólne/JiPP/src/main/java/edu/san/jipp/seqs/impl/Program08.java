// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.seqs.impl;

import edu.san.jipp.seqs.Seqs;

public class Program08 {

  public static void main(String... args) {
    // Unary<Long, Long> inc = n -> n + 1L;

    var N = Seqs.iterate(n -> n + 1L, 0L);
    System.out.println(N.first());
    System.out.println(N.rest().first());
    System.out.println(N.rest().rest().first());
    System.out.println(N.rest().rest().rest().first());
  }

}

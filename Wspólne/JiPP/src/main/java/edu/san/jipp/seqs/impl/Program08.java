// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.seqs.impl;


import java.util.List;

import edu.san.jipp.seqs.Seqs;

public class Program08 {

  public static void main(String... args) {
    var s1 = Seqs.iterate(n -> n + 1, 1)
        .map(n -> n * n)
        .take(5)
        .reduce((i, j) -> i + j, 0L);

    System.out.println(s1);

    // System.out.println(s1.take(10).asString());

    var result = List.of(1, 2, 3, 4, 5)
        .stream()
        .map(n -> n * n)
        .reduce((i, j) -> i + j);
    System.out.println(result);

  }

}

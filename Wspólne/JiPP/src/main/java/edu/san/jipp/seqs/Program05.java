package edu.san.jipp.seqs;

import java.util.List;

public class Program05 {

  public static void main(String... args) {
    final var l1 = ISeq.<String>nil()
        .cons("aaa")
        .cons("bbb")
        .cons("ccc");

    final var x = l1.rest().rest().first();

    System.out.println(l1);
    System.out.println(x);

    final var elems = List.of("a", "b", "c", "d");
    final var it = elems.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    for (final var e : elems) {
      System.out.println(e);
    }

  }

}

package edu.san.jipp.seqs;

import java.util.List;

public class Program05 {

  public static void main(String... args) {
    var l1 = ISeq.<String>nil()
        .cons("aaa")
        .cons("bbb")
        .cons("ccc");

    var x = l1.rest().rest().first();

    System.out.println(l1);
    System.out.println(x);

    var elems = List.of("a", "b", "c", "d");
    var it = elems.iterator();
    while(it.hasNext()) {
      System.out.println(it.next());
    }

    for (var e : elems) {
      System.out.println(e);
    }

  }

}

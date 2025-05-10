package edu.san.jipp.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Point2DTest {

  @Test
  void testPoint2D() {
    final var p1 = new Point2D(3, 4);
    assertEquals(3, p1.x());
    assertEquals(4, p1.y());

    final var p2 = new Point2D(3, 4);
    assertEquals(3, p2.x());
    assertEquals(4, p2.y());

    // identity check
    final var areIdentical1 = p1 == p1;
    final var areIdentical2 = p1 == p2;
    assertTrue(areIdentical1);
    assertFalse(areIdentical2);

    assertTrue(p1.equals(p1));
    assertTrue(p1.equals(p2));
    assertTrue(p2.equals(p1));

    // p2.setX(5);
    assertTrue(p2.equals(p1));
  }

  @Test
  void testPoint2DDoubleDouble() {
    final var p1 = new Point2D(1, 2);
    assertEquals(1, p1.x());
    assertEquals(2, p1.y());
  }

  @Test
  void testColoredPoint2D() {
    final var p1 = new ColoredPoint2D(Color.RED, 1, 2);
    final var p2 = new ColoredPoint2D(Color.RED, 1, 2);
    final var p3 = new ColoredPoint2D(Color.BLUE, 1, 2);
    new ColoredPoint2D(Color.RED, 4, 5);
    new ColoredPoint2D(Color.GREEN, 7, 8);

    assertTrue(p1.equals(p1));
    assertTrue(p1.equals(p2));
    assertTrue(p1.equals(p3));

    final var p6 = new Point2D(1, 2);
    assertTrue(p6.equals(p1));
    assertTrue(p1.equals(p6));

  }

}

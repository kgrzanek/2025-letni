package edu.san.jipp.classes;

import java.util.Objects;

public class ColoredPoint2D extends Point2D {

  private final Color color;

  public ColoredPoint2D(Color color, double x, double y) {
    super(x, y);
    this.color = Objects.requireNonNull(color);
  }

  public Color getColor() {
    return color;
  }

}

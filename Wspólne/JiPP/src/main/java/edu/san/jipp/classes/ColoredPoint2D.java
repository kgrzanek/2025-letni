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

//  @Override
//  public int hashCode() {
//    final var prime = 31;
//    var result = super.hashCode();
//    return prime * result + (color == null ? 0 : color.hashCode());
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (!super.equals(obj) || !(obj instanceof final ColoredPoint2D other))
//      return false;
//    return color == other.color;
//  }

}

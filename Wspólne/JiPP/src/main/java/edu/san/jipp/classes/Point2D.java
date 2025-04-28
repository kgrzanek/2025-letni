package edu.san.jipp.classes;

public class Point2D {

  public static final Point2D ORIGIN = new Point2D(0, 0);

  private final double x;

  private final double y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Point2D))
      return false;
    Point2D other = (Point2D) obj;
    return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

}

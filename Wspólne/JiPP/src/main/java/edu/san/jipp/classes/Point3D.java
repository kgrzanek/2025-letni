package edu.san.jipp.classes;

public record Point3D(double x, double y, double z) {

  public static final Point3D ORIGIN = new Point3D(0, 0, 0);

  public double length() {
    return Math.sqrt(x() * x() + y() * y() + z() * z());
  }

}

// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products.entity;

public record Product(String name) {

  public Product {
    if (name == null || name.isBlank())
      throw new IllegalArgumentException(name);
  }

}

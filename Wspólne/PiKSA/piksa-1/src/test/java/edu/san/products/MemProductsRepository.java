// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products;

import java.util.Map;
import java.util.Optional;

class MemProductsRepository implements ProductsRepository {

  final Map<String, Product> productNamesIndex = Map.of(
      "Product1", new Product("Product1"));

  @Override
  public Optional<Product> findProductByName(String name) {
    return Optional.ofNullable(productNamesIndex.get(name));
  }

}

// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products;

import java.util.Optional;

public interface ProductsRepository {

  Optional<Product> findProductByName(String name);

}
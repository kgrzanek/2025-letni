// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products.entity.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Optional;

import edu.san.O;
import edu.san.products.entity.Product;
import edu.san.products.entity.ProductsRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SQLProductsRepository implements ProductsRepository {

  private final Logger log;

  SQLProductsRepository(Logger log) {
    this.log = O.nn(log);
  }

  @Override
  public Optional<Product> findProductByName(String name) {
    if (name == null || name.isBlank())
      return Optional.empty();

    log.log(Level.INFO,
        "findProductByName: SELECT * FROM products WHERE name = " + name);
    return Optional.ofNullable(null);
  }

}

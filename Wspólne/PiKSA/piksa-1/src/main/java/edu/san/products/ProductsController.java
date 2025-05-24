// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products;

import java.lang.System.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductsController {

  private final ProductsRepository productsRepository;

  private final Logger log;

  ProductsController(ProductsRepository productsRepository, Logger log) {
    this.productsRepository = productsRepository;
    this.log = log;
  }

  public Stream<String> findExistingProductNames(
      Iterable<String> productNames) {

    log.log(System.Logger.Level.INFO,
        "findExistingProductNames called with product names");

    return StreamSupport.stream(productNames.spliterator(), false)
        .filter(name -> productsRepository.findProductByName(name).isPresent());
  }

}

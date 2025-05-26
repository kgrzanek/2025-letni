// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products.control;

import java.lang.System.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import edu.san.O;
import edu.san.products.entity.ProductsRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductsController {

  private final ProductsRepository productsRepository;

  private final Logger log;

  ProductsController(ProductsRepository productsRepository, Logger log) {
    this.productsRepository = O.nn(productsRepository);
    this.log = O.nn(log);
  }

  public Stream<String> findExistingProductNames(
      Iterable<String> productNames) {

    log.log(System.Logger.Level.INFO,
        "findExistingProductNames called with product names");

    return StreamSupport.stream(productNames.spliterator(), false)
        .filter(name -> productsRepository.findProductByName(name).isPresent());
  }

}

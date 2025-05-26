// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.products.control;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.products.entity.ProductsRepository;
import edu.san.products.entity.impl.MemProductsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ProductsControllerTest {

  ProductsController productsController;

  ProductsRepository productsRepository;

  @Inject
  Logger log;

  @BeforeEach
  void setUp() {
    productsRepository = new MemProductsRepository();
    productsController = new ProductsController(productsRepository, log);
  }

  @Test
  void testFindExistingProductNames() {
    log.log(Level.INFO,
        "Działa ProductsControllerTest.testFindExistingProductNames");
    final var names = productsController
        .findExistingProductNames(List.of("Product1", "Product2", "Product3"));

    assertThat(names)
        .contains("Product1")
        .doesNotContain("Product2", "Product3");
  }

}

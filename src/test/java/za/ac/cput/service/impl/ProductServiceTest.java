package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductServiceTest {

 @Autowired
 private ProductService service;
 private static Product product;
 private static Review Review;


 @Test
 @Order(1)
 void a_create() {



  product = ProductFactory.createProduct(
          "Hermes",
          "Beige",
          (short) 90,
          "Available",
          Review
  );

  Product created = service.create(product);
  assertNotNull(created);
  product = created; // store persisted entity
  System.out.println("Created: " + created);
 }

 @Test
 @Order(2)
 void b_read() {
  Product read = service.read(product.getProductId());
  assertNotNull(read);
  System.out.println("Read: " + read);
 }

 @Test
 @Order(3)
 void c_update() {
  Product updatedProduct = new Product.Builder()
          .copy(product)
          .setInStock("Out of Stock") // simulate update
          .build();

  Product updated = service.update(updatedProduct);
  assertNotNull(updated);
  product = updated; // store updated entity
  System.out.println("Updated: " + updated);
 }

 @Test
 @Order(4)
 void d_getAll() {
  List<Product> allProducts = service.getall();
  assertNotNull(allProducts);
  System.out.println("All Products: " + allProducts);
 }
}

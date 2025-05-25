/**
 * BabyCottonClub
 * Product.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */


package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)

class ProductServiceTest {
    private ProductService productService;

    private Product product = ProductFactory.createProduct("P54321", "Nike", "Brown", (short) 50, "available");

@Test
    void a_create() {
    Product created = productService.create(product);
    assertNotNull(created);
    System.out.println(created);
}

@Test
    void b_read(){
    Product read = productService.read(product.getProductId());
    assertNotNull(read);
    System.out.println(read);
}
@Test
    void c_update(){
    Product newProduct = new Product.Builder()
            .copy(product).build();
    productService.update(newProduct);
    Product read = productService.read(newProduct.getProductId());
    assertNotNull(read);
    System.out.println(read);
}
@Test
    void e_getall(){
    System.out.println(productService.getall());
}

}
//updated

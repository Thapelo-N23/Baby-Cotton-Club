package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String url = "http://localhost:8080";
    private static Product product;

    @BeforeAll
    public static void setUp() {
        product = ProductFactory.createProduct( "ZARA", "white", (short) 19, "out of stock");
    }

    @Test
    @Order(1)
    void createProduct() {
        String createProductUrl = url + "/product";
        ResponseEntity<Product>postResponseEntity = restTemplate.postForEntity(createProductUrl, product, Product.class);
        Product createdProduct = postResponseEntity.getBody();
        assert createdProduct != null;
        assertNotNull(product);
        System.out.println("Product created " + createdProduct);

    }

    @Test
    @Order(2)
    void readProduct() {
        int productId = product.getProductId();
        String readProductUrl = url + "/product/" + productId;
        System.out.println("Product read " + readProductUrl);
        ResponseEntity<Product> readResponseEntity = restTemplate.getForEntity(readProductUrl, Product.class);
        Product readProduct = readResponseEntity.getBody();
        assert readProduct != null;
        System.out.println("Product read " + readProduct);

    }

    @Test
    @Order(3)
    void updateProduct() {
        String updateProductUrl = url + "/product/";
        System.out.println("Product updated " + updateProductUrl);
        Product productUpdate = new Product.Builder().copy(product).build();
        HttpEntity<Product>requestEntity = new HttpEntity<>(productUpdate);
        ResponseEntity<Product> responseEntity = restTemplate.exchange(updateProductUrl, HttpMethod.POST, requestEntity, Product.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Product updatedProduct = responseEntity.getBody();
        assert updatedProduct != null;
        System.out.println("Product updated " + updatedProduct);
    }

    @Test
    @Order(4)
    void getAll() {
        String allProductsUrl = url + "/product";
        System.out.println("All products " + allProductsUrl);
        HttpEntity<String> requestEntity = new HttpEntity<>(null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(allProductsUrl, HttpMethod.GET, requestEntity, String.class);
        System.out.println("All products " + responseEntity);
    }
}
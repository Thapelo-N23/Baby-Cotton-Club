/*
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;
import za.ac.cput.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryControllerTest {

    private Category category;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/category";
    }
        @BeforeAll
    void setUp() {
        category = CategoryFactory.createCategory("Clothes");
        category = categoryRepository.save(category);

            String url = getBaseUrl() + "/create";
            ResponseEntity<Category> response = restTemplate.postForEntity(url, category, Category.class);
            assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create a category");
            category = response.getBody();
    }

    @Test
    void createCategory() {
        assertNotNull(category);
        assertNotNull(category.getCategoryId());
        System.out.println("Category created: " + category);
    }

    @Test
    void readCategory() {
        String url = getBaseUrl() + "/read/" + category.getCategoryId();
        ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(category.getCategoryId(), response.getBody().getCategoryId());
        System.out.println("Read Category: " + response.getBody());
    }
@Test
    void updateCategory() {
       Category updated = new Category.Builder()
                .copy(category)
                .setCategoryName("Updated Clothes")
                .build();

    HttpEntity<Category> request = new HttpEntity<>(updated);
    String url = getBaseUrl() + "/update";
    ResponseEntity<Category> response = restTemplate.exchange(url, HttpMethod.PUT, request, Category.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Updated Name", response.getBody().getCategoryName());

    category = response.getBody(); // update reference
    System.out.println("Updated Category: " + category);
    }
    @Test
    void getAllCategories() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<Category[]> response = restTemplate.getForEntity(url, Category[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Categories: " + response.getBody().length);
    }
}
*/

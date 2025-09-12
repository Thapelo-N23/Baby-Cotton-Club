package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Category;

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

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/category";
    }

    @BeforeAll
    void setUp() {
        Category requestCategory = new Category.Builder()
                .setCategoryName("Clothes")
                .build();

        String url = getBaseUrl() + "/create";
        ResponseEntity<Category> response = restTemplate.postForEntity(url, requestCategory, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create a category");
        category = response.getBody();
        assertNotNull(category);
        System.out.println("Test server running on: http://localhost:" + port);
    }

    @Test
    void a_createCategory() {
        assertNotNull(category);
        assertTrue(category.getCategoryId() > 0);
        System.out.println("Category created: " + category);
    }

    @Test
    void b_readCategory() {
        String url = getBaseUrl() + "/read/" + category.getCategoryId();
        ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(category.getCategoryId(), response.getBody().getCategoryId());
        System.out.println("Read Category: " + response.getBody());
    }

    @Test
    void c_updateCategory() {
        Category updated = new Category.Builder()
                .copy(category)
                .setCategoryName("Updated Clothes")
                .build();

        HttpEntity<Category> request = new HttpEntity<>(updated);
        String url = getBaseUrl() + "/update";
        ResponseEntity<Category> response = restTemplate.exchange(url, HttpMethod.PUT, request, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Clothes", response.getBody().getCategoryName());

        category = response.getBody();
        System.out.println("Updated Category: " + category);
    }

    @Test
    void d_getAllCategories() {
        String url = getBaseUrl() + "/getall";
        ResponseEntity<Category[]> response = restTemplate.getForEntity(url, Category[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All Categories: " + response.getBody().length);
    }
}

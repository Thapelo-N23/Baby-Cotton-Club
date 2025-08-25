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
import za.ac.cput.domain.Review;


import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ReviewControllerTest {
  private Review review;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    private String getBaseUrl() {
        return "http://localhost:" + port + "/review";
    }

    @BeforeAll
    void setUp() {
        Review requestReview = new Review.Builder()
                .setRating((short)5).setReviewDate(LocalDate.now()).setReviewComment("Great!")
                .build();

        String url = getBaseUrl() + "/create";
        ResponseEntity<Review> response = restTemplate.postForEntity(url, requestReview, Review.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create a review");
        review = response.getBody();
        assertNotNull(review);
        System.out.println("Test server running on: http://localhost:" + port);

    }
    @Test
    void a_createReview() {
        assertNotNull(review);
        assertTrue(review.getReviewId()>0);
        System.out.println("Review created: " + review);
    }

    @Test
    void b_readReview() {
        String url = getBaseUrl() + "/read/" + review.getReviewId();
        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(review.getReviewId(), response.getBody().getReviewId());
        System.out.println("Read Review: " + response.getBody());
    }

    @Test
    void c_updateReview() {
        Review updated = new Review.Builder().copy(review)
                .setRating((short)5).setReviewDate(LocalDate.now()).setReviewComment("Good product")
                .build();

        HttpEntity<Review> request = new HttpEntity<>(updated);
        String url = getBaseUrl() + "/update";
        ResponseEntity<Review> response = restTemplate.exchange(url, HttpMethod.PUT, request, Review.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Good product", response.getBody().getReviewComment());

        review = response.getBody();
        System.out.println("Updated Review: " + review);

    }

    @Test
    void d_getAllReviews() {

        String url = getBaseUrl() + "/getall";
        ResponseEntity<Review[]> response = restTemplate.getForEntity(url, Review[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0, "No reviews found");
        System.out.println("All Reviews: " + Arrays.toString(response.getBody()));

    }
}
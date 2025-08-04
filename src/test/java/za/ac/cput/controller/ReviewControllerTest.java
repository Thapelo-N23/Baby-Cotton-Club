//package za.ac.cput.controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import za.ac.cput.domain.Customer;
//import za.ac.cput.domain.Product;
//import za.ac.cput.domain.Review;
//import za.ac.cput.factory.ReviewFactory;
//import za.ac.cput.repository.CustomerRepository;
//import za.ac.cput.repository.ProductRepository;
//import za.ac.cput.repository.ReviewRepository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//
//class ReviewControllerTest {
//  private Review review;
//  private Product product;
//    private Customer customer;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//    private ProductRepository productRepository;
//    private CustomerRepository customerRepository;
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/review";
//    }
//
//    @BeforeAll
//    void setUp() {
//        review = ReviewFactory.createReview( (short)5, "Excellent quality and service.",
//                "2023-10-01T10:00:00Z", null, null);
//        review = reviewRepository.save(review);
//
//        String url = getBaseUrl() + "/create";
//        ResponseEntity<Review> response = restTemplate.postForEntity(url, review, Review.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Failed to create a review");
//        review = response.getBody();
//    }
//    @Test
//    void createReview() {
//        assertNotNull(review);
//        assertNotNull(review.getReviewId());
//        System.out.println("Review created: " + review);
//    }
//
//    @Test
//    void readReview() {
//        String url = getBaseUrl() + "/read/" + review.getReviewId();
//        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(review.getReviewId(), response.getBody().getReviewId());
//        System.out.println("Read Review: " + response.getBody());
//    }
//
//    @Test
//    void updateReview() {
//        Review updated = new Review.Builder()
//                .copy(review)
//                .rating((short) 4)
//                .reviewComment("Good quality, but delivery was slow.")
//                .build();
//
//        String url = getBaseUrl() + "/update";
//        ResponseEntity<Review> response = restTemplate.postForEntity(url, updated, Review.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(updated.getReviewId(), response.getBody().getReviewId());
//        System.out.println("Updated Review: " + response.getBody());
//    }
//
//    @Test
//    void getAllReviews() {
//        String url = getBaseUrl() + "/all";
//        ResponseEntity<Review[]> response = restTemplate.getForEntity(url, Review[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0, "No reviews found");
//        System.out.println("All Reviews: " + response.getBody());
//    }
//}
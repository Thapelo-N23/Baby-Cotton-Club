package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import java.util.Collections;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewFactoryTest {

    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
    );

    public static Review review = ReviewFactory.createReview(
            (short) 4,
            "Great service!",
            "20250503",
            customer
    );

    @Test
    @Order(1)
    public void createReview() {
        Assertions.assertNotNull(review);
        System.out.println(review);
    }
}

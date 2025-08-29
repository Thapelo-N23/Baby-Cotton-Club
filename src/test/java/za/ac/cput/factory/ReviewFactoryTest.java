/*
Review.java
ReviewFactoryTest POJO class
Author: Olwethu Nene
Student number:(230277845)
Date: 20 June 2025
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Supplier;

import java.util.Collections;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewFactoryTest {

    private static Customer customer1 = CustomerFactory.createCustomer("John", "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
    );
    private static Supplier supplier = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            null);

    static Product product1 = ProductFactory.createProduct("Lancewood", "Yellow", (short) 588, "OUT OF STOCK", null,supplier);
    public static Review review1 = ReviewFactory.createReview(
            (short) 4,
            "Great service!",
            "20250503",
            customer1,product1);

    @Test
    @Order(1)
    public void testCreateReview() {
        assertNotNull(review1);
        System.out.println(review1);

    }

    @Test
    @Order(2)
    public void testCustomerWithReview() {
        Review reviewWithCustomer = ReviewFactory.createReview((short) 5, "Excellent product!", "20250101", customer1, product1);
        assertNotNull(reviewWithCustomer);
        assertEquals("Excellent product!", reviewWithCustomer.getReviewComment());
        assertEquals(customer1, reviewWithCustomer.getCustomer());
        System.out.println("Review with Customer: " + reviewWithCustomer);

    }
    @Test
    @Order(3)
    public void testProductWithReview() {
        Review reviewWithProduct = ReviewFactory.createReview((short) 5, "Excellent product!", "20250101", customer1, product1);
        assertNotNull(reviewWithProduct);
        assertEquals("Excellent product!", reviewWithProduct.getReviewComment());
        assertEquals(product1, reviewWithProduct.getProduct());
        System.out.println("Review with Product: " + reviewWithProduct);
    }

}
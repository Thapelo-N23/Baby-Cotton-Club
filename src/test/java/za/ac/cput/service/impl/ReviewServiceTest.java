package za.ac.cput.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.ICustomerService;
import za.ac.cput.service.IReviewService;
import za.ac.cput.service.ISupplierService;
import za.ac.cput.service.ProductService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ReviewServiceTest {
    @Autowired
    private IReviewService service;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ISupplierService supplierService;

    private Review review;
    private Product product;
    private Supplier supplier;
    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            null
        );
        customer = customerService.create(customer);

        cart = CartFactory.createCart(customer, cartItems);
        // If you have a CartService, persist it here
        // cart = cartService.create(cart);
        customer.setCart(cart);
        customer = customerService.update(customer);

        supplier = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            null
        );
        supplier = supplierService.create(supplier);

        product = ProductFactory.createProduct(
            "Nike",
            "Blue",
            (short)67,
            "Available",
            null,
            supplier
        );
        product = productService.create(product);

        review = ReviewFactory.createReview(
            (short)5,
            "Great product",
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
            customer,
            product
        );
        review = service.create(review);
    }

    @Test
    @Order(1)
    void create() {
        assertNotNull(review);
        assertNotNull(review.getReviewId());
        System.out.println("Created Review: " + review);
    }

    @Test
    @Order(2)
    void read() {
        Review readReview = service.read(review.getReviewId());
        assertNotNull(readReview);
        System.out.println("Read Review: " + readReview);
    }

    @Test
    @Order(3)
    void update() {
        Review updatedReview = new Review.Builder()
            .copy(review)
            .setRating((short) 4)
            .setReviewComment("Good product")
            .setCustomer(customer)
            .setProduct(product)
            .build();
        Review updated = service.update(updatedReview);
        assertNotNull(updated);
        review = updated;
        System.out.println("Updated Review: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Review> allReviews = service.getAll();
        assertNotNull(allReviews);
        assertFalse(allReviews.isEmpty());
        System.out.println("All Reviews: " + allReviews);
    }
}
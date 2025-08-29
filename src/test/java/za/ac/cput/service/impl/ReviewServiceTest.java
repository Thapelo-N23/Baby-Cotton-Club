package za.ac.cput.service.impl;

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
import java.util.Arrays;
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

    private static Review review;

    private static Product product;
    private static Supplier supplier;
    private static List<CartItem> cartItems = new ArrayList<>();

    private static Cart cart = CartFactory.createCart(null, cartItems);

    private static Customer customer = CustomerFactory.createCustomer(
            "John",
            "Doe",
            "mengezi@gmail.com",
            "0781234567",
            "securePassword123",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            cart

    );

    @Test
    @Order(1)
    void create() {

        Customer create = customerService.create(customer);
        assertNotNull(create);
        customer = create;
        System.out.println("Created Customer" + create);

        supplier = SupplierFactory.createSupplier(
                "SnuggleBabies Clothing Co.",
                "0211234567",
                null);
        supplier = supplierService.create(supplier);
        assertNotNull(supplier);
        System.out.println("Created Supplier: " + supplier);

        product = ProductFactory.createProduct("Nike","Blue",
                (short)67,"Available",null,supplier);
        Product create1 = productService.create(product);
        assertNotNull(create1);
        product = create1;
        System.out.println("Product created: " + create1);

        review = ReviewFactory.createReview( (short)5,"Great product",
                 LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),customer, product);
        Review created = service.create(review);
        assertNotNull(created);
        review = created;
        System.out.println("Created Review: " + created);

    }

    @Test
    @Order(2)
    void read() {
       // assertNotNull(review, "Review object from create() should not be null");
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
        System.out.println("Updated Review: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(service.getAll());
        System.out.println("All Reviews: " + review);
    }
}
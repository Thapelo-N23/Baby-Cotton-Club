/*
SupplierFactoryTest POJO Class
Author: Phindile Lisa Ngozi
Student Number: 230640893
Date: 2025/05/18
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Supplier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierFactoryTest {

    private static final Product testProduct = new Product();



    private static final Supplier supplier1 = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567"

    );

    private static final Supplier supplier2 = SupplierFactory.createSupplier(
            "Tiny Togs Supplies",
            "0210987654"

    );

    @Test
    @Order(1)
    void createSupplier() {
        assertNotNull(supplier1);
        System.out.println(supplier1);
    }

    @Test
    @Order(2)
    void createAnotherSupplier() {
        assertNotNull(supplier2);
        System.out.println(supplier2);
    }

    @Test
    @Order(3)
    void testCreateSupplierWithNullName() {
        Supplier invalid = SupplierFactory.createSupplier(
                null,
                "0210836543"

        );
        assertNull(invalid, "Supplier with null name should be null");
        System.out.println(invalid);
    }

    @Test
    @Order(4)
    void testCreateSupplierWithNullContact() {
        Supplier invalid = SupplierFactory.createSupplier(
                "CuddleWear Baby Co.",
                null

        );
        assertNull(invalid, "Supplier with null contact should be null");
        System.out.println(invalid);
    }


    @Test
    @Order(6)
    void createSupplierWithProducts() {
        Product product = new Product.Builder()
                .setProductName("TestProduct")
                .setColor("Blue")
                .setPrice((short) 100)
                .setInStock("IN STOCK")
                .build();
        Supplier supplier = SupplierFactory.createSupplier(
                "SupplierWithProducts",
                "0210000000",
                java.util.Collections.singletonList(product)
        );
        assertNotNull(supplier);
        assertNotNull(supplier.getProducts());
        assertEquals(1, supplier.getProducts().size());
        assertEquals("TestProduct", supplier.getProducts().get(0).getProductName());
        System.out.println(supplier);
    }
}

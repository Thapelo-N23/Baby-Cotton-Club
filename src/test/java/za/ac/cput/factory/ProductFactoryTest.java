/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {
    private static Review review = new Review.Builder()
            .setRating((short) 5)
            .setReviewComment("Great quality")
            .build();
    private static Supplier supplier = SupplierFactory.createSupplier(
            "SnuggleBabies Clothing Co.",
            "0211234567",
            null);

    private static Product p1 = ProductFactory.createProduct("Lancewood", "Yellow", (short) 588, "OUT OF STOCK", review,supplier);
    private static Product p2 = ProductFactory.createProduct("Coke", "Red", (short) 900, "IN-STOCK", review,supplier);

    @Test
    @Order(1)
    public void testCreateProduct() {
    assertNotNull(p1);
    System.out.println(p1);
    }
@Test
    @Order(2)
    public void testCreateProductWithAllAttributes() {
    assertNotNull(p2);
    System.out.println(p2);
    System.out.println(p1);
    }
@Test
    @Order(3)
    public void testCreateProductThatFails() {
    assertNotNull(p1);
    System.out.println(p1);
    }
@Test
    @Order(4)
    public void testCreateProductWithSupplier() {
        za.ac.cput.domain.Supplier supplier = new za.ac.cput.domain.Supplier.Builder()
                .setSupplierName("SupplierTest")
                .setContactDetails("0215555555")
                .build();
        Product product = ProductFactory.createProduct(
                "SupplierProduct",
                "Green",
                (short) 200,
                "IN STOCK",
                review,
                supplier
        );
        assertNotNull(product);
        assertNotNull(product.getSupplier());
        assertEquals("SupplierTest", product.getSupplier().getSupplierName());
        System.out.println(product);
    }
}

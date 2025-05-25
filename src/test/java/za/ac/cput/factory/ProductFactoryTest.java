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

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {
private static Product p1 = ProductFactory.createProduct("P1", "Lancewood", "Yellow", new Short(String.valueOf(50)), "OUT OF STOCK");
private static Product p2 = ProductFactory.createProduct("p2", "Coke", "red", new Short("R" + 500), "OUT OF STOCK");

@Test
    @Order(1)
    public void testCreateProduct() {
    assertNotNull(p1);
    System.out.println(p1);
    }
@Test
    @Order(2)
    public void testCreateProductWithAllAttributes() {
    assertNotNull(p1);
    System.out.println(p1);
    }
@Test
    @Order(3)
    public void testCreateProductThatFails() {
    assertNotNull(p1);
    System.out.println(p1);
    }
}
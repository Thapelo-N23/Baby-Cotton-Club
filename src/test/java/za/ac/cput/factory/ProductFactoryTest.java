/**
 * BabyCottonClub
 * Shipment.java
 * Author : Thapelo Ngwenya - 222260971
 * Date : 11 May 2025
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {
    private static Product p1 = ProductFactory.createProduct(
            "Lancewood", "Yellow", (short) 50, "OUT OF STOCK"
    );
    private static Product p2 = ProductFactory.createProduct(
            "Coke", "Red", (short) 500, "OUT OF STOCK"
    );

    @Test

    public void testCreateProduct() {
        assertNotNull(p1);
        System.out.println(p1);
    }

    @Test

    public void testCreateProductWithAllAttributes() {
        assertNotNull(p2);
        System.out.println(p2);
    }


}

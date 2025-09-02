/**
 * BabyCottonClub
 * CartItemFactoryTest.java
 * Author : Onako Ntsaluba - 230741754
 */

package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CartItemFactoryTest {
    private static Customer customer = CustomerFactory.createCustomer(
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
    private static Cart cart = CartFactory.createCart(customer, null);

    private static Product product = ProductFactory.createProduct(
            "Test Product",
            "Blue",
            (short) 100,
            "IN STOCK",
            null,
            null
    );
    int quantity = 2;


    @Test
    void createCartItem() {

        CartItem cartItem = CartItemFactory.createCartItem(cart, product, quantity);
        assertNotNull(cartItem, "CartItem should not be null");
        assertEquals(cart, cartItem.getCart());
        assertEquals(product, cartItem.getProduct());
        assertEquals(quantity, cartItem.getQuantity());
        System.out.println(cartItem);
    }


    @Test
    void createCartItemWithProduct() {
        CartItem cartItem = CartItemFactory.createCartItem(cart, product, quantity);
        assertNotNull(cartItem, "CartItem should not be null when product is valid");
        assertEquals(product, cartItem.getProduct());
        System.out.println(cartItem);
    }

    @Test
    void createCartItemWithInvalidQuantity() {
        int quantity = 0;
        CartItem cartItem = CartItemFactory.createCartItem(cart, product, quantity);
        assertNull(cartItem, "CartItem should be null for invalid quantity");
    }
}

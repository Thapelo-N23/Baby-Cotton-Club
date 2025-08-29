package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {
   public static Customer customer = CustomerFactory.createCustomer(
            "Jane",
            "Smith",
            "jane.smith@gmail.com",
            "0712345678",
            "anotherPassword456",
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            null
    );
    @Test
    void createCart() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = CartItemFactory.createCartItem(null, null, 2); // Adjust as needed for your domain
        cartItems.add(cartItem);

        Cart cart = CartFactory.createCart(customer, cartItems);
        assertNotNull(cart, "Cart should not be null");
        assertEquals(customer, cart.getCustomer());
        assertEquals(cartItems, cart.getItems());
        assertFalse(cart.isCheckedOut());
        System.out.println(cart);
    }

    @Test
    void createCartWithCustomer() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(CartItemFactory.createCartItem(null, null, 1));

        Cart cart = CartFactory.createCart(customer, cartItems);
        assertNotNull(cart, "Cart should not be null when customer is valid");
        assertEquals(customer, cart.getCustomer());
        assertEquals(cartItems, cart.getItems());
        System.out.println(cart);
    }

    @Test
    void createCartWithEmptyItems() {
        List<CartItem> cartItems = new ArrayList<>();

        Cart cart = CartFactory.createCart(customer, cartItems);
        assertNotNull(cart, "Cart should not be null with empty items");
        assertTrue(cart.getItems().isEmpty());
        assertEquals(customer, cart.getCustomer());
        System.out.println(cart);
    }
}

package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.ICartService;
import za.ac.cput.service.ICustomerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartServiceTest {

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICustomerService customerService;

    private Customer customer;
    private Cart cart;

    @BeforeEach
    void setUp() {
        customer = customerService.create(
                CustomerFactory.createCustomer(
                        "Jane",
                        "Smith",
                        "jane.smith@gmail.com",
                        "0712345678",
                        "anotherPassword456",
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        null
                )
        );
        List<CartItem> cartItems = new ArrayList<>();
        Cart cartToCreate = CartFactory.createCart(customer, cartItems);
        cart = cartService.create(cartToCreate);
    }

    @Test
    @Order(1)
    void create() {
        assertNotNull(cart, "Created cart should not be null");
        assertNotNull(cart.getCartId(), "Cart ID should not be null");
        System.out.println("Created Cart: " + cart);
    }

    @Test
    @Order(2)
    void read() {
        Cart read = cartService.read(cart.getCartId());
        assertNotNull(read);
        System.out.println("Read Cart: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Cart updatedCart = new Cart.Builder()
                .setCartId(cart.getCartId())
                .setCustomer(cart.getCustomer())
                .setItems(cart.getItems())
                .setCheckedOut(true)
                .build();
        Cart updated = cartService.update(updatedCart);
        assertTrue(updated.isCheckedOut());
        System.out.println("Updated Cart: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Cart> allCarts = cartService.getAll();
        assertNotNull(allCarts);
        assertFalse(allCarts.isEmpty());
        System.out.println("All Carts: " + allCarts);
    }
}

/**
 * BabyCottonClub
 * CartItemServiceTest.java
 * Author : Mengezi Junior Ngwenya - 230023967
 * Date : 29 August 2025
 */

package za.ac.cput.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.ICartItemService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemServiceTest {

    @Autowired
    private ICartItemService cartItemService;

    private static CartItem cartItem;
    private static Cart cart;
    private static Product product;
    private static Category category;
    private static Customer customer;

    @Test
    void a_create() {
        category = CategoryFactory.createCategory(
                "Blankets",
                null
        );

        product = ProductFactory.createProduct(
                "Baby Blanket",
                "Soft cotton blanket",
                (short)150,
                "yes",
                null,
                null
        );


        customer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "mengezi@gmail.com",
                "0781234567",
                "securePassword123",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()

        );
        cart = CartFactory.createCart(
                customer,
                Arrays.asList()
        );

        cartItem = CartItemFactory.createCartItem(
                cart,
                product,
                5
        );

        CartItem created = cartItemService.create(cartItem);
        assertNotNull(created);
        cartItem = created;
        System.out.println("Created CartItem: " + cartItem);
    }

    @Test
    void b_read() {
        CartItem readItem = cartItemService.read(cartItem.getCartItemId());
        assertNotNull(readItem);
        System.out.println("Read CartItem: " + readItem);
    }

    @Test
    void c_update() {
        CartItem updated = new CartItem.Builder()
                .copy(cartItem)
                .setQuantity(7)
                .build();

        CartItem result = cartItemService.update(updated);
        assertNotNull(result);
        cartItem = result;
        System.out.println("Updated CartItem: " + cartItem);
    }

    @Test
    void d_getAll() {
        List<CartItem> allItems = cartItemService.getAll();
        assertNotNull(allItems);
        System.out.println("All CartItems: " + allItems);
    }
}

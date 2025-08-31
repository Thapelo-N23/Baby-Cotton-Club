/**
 * BabyCottonClub
 * CartController.java
 * Author : Onako Ntsaluba - 230741754
 * Date : 24 May 2025
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.impl.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Cart createCart(@RequestBody Cart cart) {
        return service.create(cart);
    }

    @GetMapping("/read/{id}")
    public Cart readCart(@PathVariable("id") Integer cartId) {
        return service.read(cartId);
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestBody Cart cart) {
        return service.update(cart);
    }

    @GetMapping("/findAll")
    public List<Cart> getAllCarts() {
        return service.getAll();
    }
}

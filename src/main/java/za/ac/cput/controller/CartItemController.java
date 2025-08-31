/**
 * BabyCottonClub
 * CartItemController.java
 * Author : Onako Ntsaluba - 230741754
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CartItem;
import za.ac.cput.service.impl.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    private final CartItemService service;

    @Autowired
    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return service.create(cartItem);
    }

    @GetMapping("/read/{id}")
    public CartItem readCartItem(@PathVariable("id") Integer cartItemId) {
        return service.read(cartItemId);
    }

    @PutMapping("/update")
    public CartItem updateCartItem(@RequestBody CartItem cartItem) {
        return service.update(cartItem);
    }

    @GetMapping("/findAll")
    public List<CartItem> getAllCartItems() {
        return service.getAll();
    }
}

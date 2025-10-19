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
import za.ac.cput.repository.ProductRepository;
import org.springframework.http.ResponseEntity;

import za.ac.cput.domain.Product;

import java.util.List;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    private final CartItemService service;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCartItem(@RequestBody CartItem cartItem) {
        try {
            if (cartItem.getProduct() == null || cartItem.getProduct().getProductId() == 0) {
                return ResponseEntity.badRequest().body("Product must be specified");
            }
            Product product = productRepository.findById(cartItem.getProduct().getProductId()).orElse(null);
            if (product == null) return ResponseEntity.badRequest().body("Product not found: " + cartItem.getProduct().getProductId());
            String sz = cartItem.getSize();
            if (product.getSizes() != null && !product.getSizes().isEmpty()) {
                if (sz == null || sz.isEmpty() || !product.getSizes().contains(sz)) {
                    return ResponseEntity.badRequest().body("Invalid or missing size '" + sz + "' for product: " + product.getProductId());
                }
            }
            CartItem created = service.create(cartItem);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
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

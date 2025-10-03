/**
 * BabyCottonClub
 * CartController.java
 * Author : Onako Ntsaluba - 230741754
 * Date : 24 May 2025
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.dto.CartRequest;
import za.ac.cput.dto.CartUpdateRequest;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.impl.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService service;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CartController(CartService service, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.service = service;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody CartRequest request) {
        try {
            Cart cart = service.create(request);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            System.err.println("Cart creation failed: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/read/{id}")
    public Cart readCart(@PathVariable("id") Integer cartId) {
        return service.read(cartId);
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestBody CartUpdateRequest request) {
        Cart cart = new Cart();
        cart.setCartId(request.getCartId());
        Customer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found: " + request.getCustomerId()));
        cart.setCustomer(customer);
        List<CartItem> items = new java.util.ArrayList<>();
        for (CartUpdateRequest.CartItemRequest itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + itemReq.getProductId()));
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            // Set other CartItem fields if needed
            items.add(item);
        }
        cart.setItems(items);
        return service.update(cart);
    }

    @GetMapping("/findAll")
    public List<Cart> getAllCarts() {
        return service.getAll();
    }
}

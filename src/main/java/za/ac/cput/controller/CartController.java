/**
 * BabyCottonClub
 * CartController.java
 * Author : Onako Ntsaluba - 230741754
 * Date : 24 May 2025
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
import za.ac.cput.repository.CartRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService service;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

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
    public ResponseEntity<?> updateCart(@RequestBody CartUpdateRequest request) {
        // Ensure cartId is provided and exists
        if (request.getCartId() <= 0) {
            return ResponseEntity.badRequest().body("Invalid cartId: " + request.getCartId());
        }
        if (!cartRepository.existsById(request.getCartId())) {
            return ResponseEntity.status(404).body("Cart not found: " + request.getCartId());
        }
        if (request.getCustomerId() == null || request.getCustomerId() <= 0) {
            return ResponseEntity.badRequest().body("Invalid customerId: " + request.getCustomerId());
        }
        Cart cart = new Cart();
        cart.setCartId(request.getCartId());
        Customer customer = customerRepository.findById(request.getCustomerId())
            .orElse(null);
        if (customer == null) {
            return ResponseEntity.badRequest().body("Customer not found: " + request.getCustomerId());
        }
        cart.setCustomer(customer);
        List<CartItem> items = new java.util.ArrayList<>();
        for (CartUpdateRequest.CartItemRequest itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + itemReq.getProductId()));
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            // validate and set size: if the product has sizes configured, size is required
            String selectedSize = itemReq.getSize();
            if (product.getSizes() != null && !product.getSizes().isEmpty()) {
                if (selectedSize == null || selectedSize.isEmpty() || !product.getSizes().contains(selectedSize)) {
                    return ResponseEntity.badRequest().body("Invalid or missing size '" + selectedSize + "' for product: " + product.getProductId());
                }
                item.setSize(selectedSize);
            }
            // Set other CartItem fields if needed
            items.add(item);
        }
        cart.setItems(items);
        try {
            Cart updated = service.update(cart);
            return ResponseEntity.ok(updated);
        } catch (ObjectOptimisticLockingFailureException e) {
            // Another transaction updated the same cart concurrently
            return ResponseEntity.status(409).body("Cart was updated by another process. Please reload and try again.");
        } catch (RuntimeException e) {
            // Service may throw RuntimeException("Cart not found: <id>") - return 404
            if (e.getMessage() != null && e.getMessage().contains("Cart not found")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            // Unknown runtime exception - return 500
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public List<Cart> getAllCarts() {
        return service.getAll();
    }
}

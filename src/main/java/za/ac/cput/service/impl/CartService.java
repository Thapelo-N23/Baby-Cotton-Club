/*
 * Baby Cotton Club
 * CartService.java
 * Author: Onako Ntsaluba
 * Student Number: 230741754
 * Date: 2025/05/24
 */

package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.CartItem;
import za.ac.cput.dto.CartItemRequest;
import za.ac.cput.dto.CartRequest;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.ProductRepository;

import java.util.List;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart create(CartRequest request) {
        Customer customer = customerRepository.findById(request.getCustomer().getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found: " + request.getCustomer().getCustomerId()));
        Cart cart = new Cart.Builder()
            .setCustomer(customer)
            .setCheckedOut(request.isCheckedOut())
            .build();

        List<CartItem> cartItems = new java.util.ArrayList<>();
        for (CartItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + itemRequest.getProductId()));
            String selectedSize = itemRequest.getSize();
            if (selectedSize == null || selectedSize.isEmpty() || product.getSizes() == null || !product.getSizes().contains(selectedSize)) {
                throw new RuntimeException("Invalid size: " + selectedSize + " for product: " + product.getProductId());
            }
            CartItem cartItem = new CartItem.Builder()
                .setCart(cart)
                .setProduct(product)
                .setQuantity(itemRequest.getQuantity())
                .setSize(selectedSize)
                .build();
            cartItems.add(cartItem);
        }
        cart.setItems(cartItems);
        return cartRepository.save(cart);
    }

    public Cart read(Integer id) {
        return this.cartRepository.findById(id).orElse(null);
    }

    public Cart update(Cart cart) {
        if (cart == null || cart.getCartId() == 0) {
            throw new IllegalArgumentException("Cart must have a valid id to update");
        }
        Cart existing = this.cartRepository.findById(cart.getCartId())
            .orElseThrow(() -> new RuntimeException("Cart not found: " + cart.getCartId()));

        // Update simple fields
        existing.setCheckedOut(cart.isCheckedOut());
        if (cart.getCustomer() != null && cart.getCustomer().getCustomerId() > 0) {
            Customer managedCustomer = customerRepository.findById(cart.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + cart.getCustomer().getCustomerId()));
            existing.setCustomer(managedCustomer);
        }

        // Replace items: ensure each item references the managed Cart
        if (cart.getItems() != null) {
            for (CartItem it : cart.getItems()) {
                it.setCart(existing);
            }
            existing.setItems(cart.getItems());
        }

        return this.cartRepository.save(existing);
    }

    public List<Cart> getAll() {
        return this.cartRepository.findAll();
    }
}

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
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.service.ICartService;

import java.util.List;

@Service
public class CartService implements ICartService {

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart read(Integer id) {
        return this.cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAll() {
        return this.cartRepository.findAll();
    }
}

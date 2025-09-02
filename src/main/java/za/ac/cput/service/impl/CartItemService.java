/**
 * BabyCottonClub
 * CartItemServiceImpl.java
 * Author : Onako Ntsaluba - 230741754
 */

package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartItemRepository;
import za.ac.cput.service.ICartItemService;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem create(CartItem cartItem) {
        return this.cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem read(Integer id) {
        return this.cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        return this.cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getAll() {
        return this.cartItemRepository.findAll();
    }
}


package za.ac.cput.service;

import za.ac.cput.domain.CartItem;
import java.util.List;

public interface ICartItemService extends IService<CartItem, Integer> {
    List<CartItem> getAll();
}


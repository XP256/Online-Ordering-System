package com.imooc.sell.service;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.Item;

import java.math.BigDecimal;
import java.util.Collection;

public interface CartService {
    void addItem(CartDTO cartDTO);
    void removeItem(String productId);
    void updateQuantity(String productId, Integer quantity);

    Collection<Item> findAll();

    //void  checkout(User user);

    BigDecimal getTotal();
}

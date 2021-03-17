package com.github.andreyaleshin.computer_store.service.impl;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void removeProduct(Product product) {

    }

    @Override
    public void clearCart() {

    }

    @Override
    public void checkout() {

    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
        return null;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

}

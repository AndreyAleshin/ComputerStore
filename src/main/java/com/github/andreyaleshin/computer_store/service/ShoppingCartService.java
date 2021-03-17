package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    void clearCart();

    void checkout();

    Map<Product, Integer> getProductsInCart();

    BigDecimal getTotalPrice();

}

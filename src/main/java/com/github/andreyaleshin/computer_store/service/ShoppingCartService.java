package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public interface ShoppingCartService {

    void addProduct(Optional<Product> product);

    void removeProduct(Optional<Product> product);

    void clearCart();

    void checkout();

    Map<Optional<Product>, Integer> getProductsInCart();

    BigDecimal getTotalPrice();

}

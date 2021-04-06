package com.github.andreyaleshin.computer_store.service.impl;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.service.ShoppingCartService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Map<Optional<Product>, Integer> shoppingCart = new LinkedHashMap<>();

    @Override
    public void addProduct(Optional<Product> product) {
        if (shoppingCart.containsKey(product)) {
            shoppingCart.replace(product, shoppingCart.get(product) + 1);
        } else {
            shoppingCart.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Optional<Product> product) {
        if (shoppingCart.containsKey(product)) {
            if (shoppingCart.get(product) > 1) {
                shoppingCart.replace(product, shoppingCart.get(product) - 1);
            } else if (shoppingCart.get(product) == 1) {
                shoppingCart.remove(product);
            }
        }
    }

    @Override
    public void clearCart() {
        shoppingCart.clear();
    }

    // no implementation yet
    @Override
    public void checkout() {
        shoppingCart.clear();
    }

    @Override
    public Map<Optional<Product>, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(shoppingCart);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return shoppingCart.entrySet().stream()
                .map(k -> k.getKey().get().getPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}

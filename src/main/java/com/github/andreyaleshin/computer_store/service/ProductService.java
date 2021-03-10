package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product);

    void edit(Long id, Product newProduct);

    void delete(Long id);

    Optional<Product> findById(Long id);

    List<Product> findAllByOrderByIdAsc();

    Long productCount();

}

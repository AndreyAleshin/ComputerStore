package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void saveProduct(Product product);

    void editProduct(Long id, Product newProduct);

    void deleteProductById(Long id);

    long productCount();

    Optional<Product> findProductById(Long id);

    List<Product> findAllProductsByOrderByIdAsc();

}

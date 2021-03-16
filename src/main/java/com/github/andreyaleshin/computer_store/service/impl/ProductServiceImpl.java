package com.github.andreyaleshin.computer_store.service.impl;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.repository.ProductRepository;
import com.github.andreyaleshin.computer_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void editProduct(Long id, Product newProduct) {
        Product existingProduct = productRepository.getOne(id);
        existingProduct.setName(newProduct.getName());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setImageUrl(newProduct.getImageUrl());
        existingProduct.setPrice(newProduct.getPrice());
        saveProduct(newProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public long productCount() {
        return productRepository.count();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProductsByOrderByIdAsc() {
        return productRepository.findAllByOrderByIdAsc();
    }

}

package com.github.andreyaleshin.computer_store.controller;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/all")
    public String showProductPage(Model model) {
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productsCount());
        return "/product";
    }

    @GetMapping("/product/details/{id}")
    public String showProductDetails(@PathVariable("id") long id, Model model) {
        Optional<Product> product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "/product-details";
    }

    private List<Product> getAllProducts() {
        return productService.findAllProductsByOrderByIdAsc();
    }

    private long productsCount() {
        return productService.productCount();
    }

}

package com.github.andreyaleshin.computer_store.controller;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.service.ProductService;
import com.github.andreyaleshin.computer_store.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductValidator productValidator;

    @Autowired
    public ProductController(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("productForm", product);
        model.addAttribute("method", "new");
        return "/product";
    }

    @PostMapping("/product/new")
    public String newProduct(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult,
                             Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "new");
            return "/product";
        }

        productService.save(productForm);
        log.debug(String.format("Product with id %s successfully created.", productForm.getId()));

        return "redirect:/home";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        Optional<Product> product = productService.findById(productId);

        if (product != null) {
            model.addAttribute("productForm", product);
            model.addAttribute("method", "edit");
            return "/product";
        } else {
            return "/error/404";
        }
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId,
                              @ModelAttribute("productForm") Product productForm,
                              BindingResult bindingResult,
                              Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "edit");
            return "/product";
        }

        productService.edit(productId, productForm);
        log.debug(String.format("Product with id %s has been successfully updated.", productId));

        return "redirect:/home";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        Optional<Product> product = productService.findById(productId);

        if (product != null) {
//            log.debug(String.format("Product with id %s successfully deleted.", product.getId())); // !!!!
            productService.delete(productId);
            return "redirect:/home";
        } else {
            return "/error/404";
        }
    }

}

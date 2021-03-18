package com.github.andreyaleshin.computer_store.controller.admin;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.service.ProductService;
import com.github.andreyaleshin.computer_store.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
public class AdminProductController {

    private final ProductService productService;
    private final ProductValidator productValidator;

    @Autowired
    public AdminProductController(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @GetMapping("/admin/product-list")
    public String showAllProducts(Model model) {
        model.addAttribute("productList", productService.findAllProductsByOrderByIdAsc());
        return "/admin/product-list";
    }

    @GetMapping("/admin/product-list/product-add")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("productFormAdd", product);
        return "/admin/product-add";
    }

    @PostMapping("/admin/product-list/product-add")
    public String newProduct(@ModelAttribute("productFormAdd") @Valid Product productForm,
                             BindingResult bindingResult,
                             Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getFieldError()));
            return "/admin/product-add";
        }

        productService.saveProduct(productForm);
        log.debug(String.format("Product with id %s successfully created.", productForm.getId()));

        return "redirect:/admin/product-list";
    }

    // id сделать long (примитив)???
    @GetMapping("/admin/product-list/product-edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);

        if (product.isPresent()) {
            model.addAttribute("productFormEdit", product);
            return "/admin/product-edit";
        } else {
            return "/error/404";
        }
    }

    @PostMapping("/admin/product-list/product-edit/{id}")
    public String editProduct(@PathVariable("id") Long productId,
                              @ModelAttribute("productFormEdit") @Valid Product productForm,
                              BindingResult bindingResult,
                              Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getFieldError()));
            return "/admin/product-edit";
        }

        productService.editProduct(productId, productForm);
        log.debug(String.format("Product with id %s has been successfully updated.", productId));

        return "redirect:/admin/product-list";
    }

    @PostMapping("/admin/product-list/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        Optional<Product> product = productService.findProductById(productId);

        if (product.isPresent()) {
            productService.deleteProductById(productId);
            log.debug(String.format("Product with id %s successfully deleted.", product.get().getId()));
            return "redirect:/admin/product-list";
        } else {
            return "/error/404";
        }
    }

}

package com.github.andreyaleshin.computer_store.controller;

import com.github.andreyaleshin.computer_store.entity.Product;
import com.github.andreyaleshin.computer_store.service.ProductService;
import com.github.andreyaleshin.computer_store.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Slf4j
@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shopping-cart")
    public String showCart(Model model) {
        model.addAttribute("products", shoppingCartService.getProductsInCart());
        model.addAttribute("totalCost", shoppingCartService.getTotalPrice());

        return "/shopping-cart";
    }

    @GetMapping("/shopping-cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id) {
        Optional<Product> product = productService.findProductById(id);

        if (product.isPresent()) {
            shoppingCartService.addProduct(product);
            log.debug(String.format("Product with id: %s added to shopping cart.", id));
        }

        return "redirect:/product/all";
    }

    @GetMapping("/shopping-cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id) {
        Optional<Product> product = productService.findProductById(id);

        if (product.isPresent()) {
            shoppingCartService.removeProduct(product);
            log.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }

        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/clear")
    public String clearProductsInCart() {
        shoppingCartService.clearCart();
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/checkout")
    public String cartCheckout() {
        shoppingCartService.checkout();
        return "redirect:/shopping-cart";
    }

}

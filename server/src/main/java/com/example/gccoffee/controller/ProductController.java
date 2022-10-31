package com.example.gccoffee.controller;

import com.example.gccoffee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;


    @GetMapping("/api/product/{id}")
    public String productPage(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping("/api/products")
    public String productsPage(Model model) {
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/api/new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/api/products")
    public String newProduct(CreateProductRequest createProductRequest) {
        productService.createProduct(
                createProductRequest.getProductName(),
                createProductRequest.getCategory(),
                createProductRequest.getPrice(),
                createProductRequest.getDescription());
        return "redirect:/api/products";
    }
}

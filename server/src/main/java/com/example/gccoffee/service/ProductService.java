package com.example.gccoffee.service;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(String productName, Category category, long price, String description) {
        var product = new Product(productName, category, price, description);
        return productRepository.save(product);
    }
}

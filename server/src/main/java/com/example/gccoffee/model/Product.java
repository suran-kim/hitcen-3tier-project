package com.example.gccoffee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;
    private Category category;
    private long price;
    private String description;

    public Product(String productName, Category category, long price, String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }
}

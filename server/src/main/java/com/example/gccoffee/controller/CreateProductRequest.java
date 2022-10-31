package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class CreateProductRequest {
    private String productName;
    private Category category;
    private long price;
    private String description;
}

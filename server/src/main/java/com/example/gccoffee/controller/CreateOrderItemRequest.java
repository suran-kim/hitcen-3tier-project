package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.OrderItem;
import com.example.gccoffee.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;


@Data
@AllArgsConstructor
public class CreateOrderItemRequest {
    private int productId;
    private Category category;
    private long price;
    private int quantity;
}

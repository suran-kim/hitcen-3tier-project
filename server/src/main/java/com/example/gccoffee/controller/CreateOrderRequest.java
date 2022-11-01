package com.example.gccoffee.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CreateOrderRequest {
    private String email;
    private String address;
    private String postcode;
    private List<CreateOrderItemRequest> orderItems;
}

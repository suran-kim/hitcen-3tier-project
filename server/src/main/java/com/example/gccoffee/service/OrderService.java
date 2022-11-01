package com.example.gccoffee.service;


import com.example.gccoffee.controller.CreateOrderItemRequest;
import com.example.gccoffee.controller.CreateOrderRequest;
import com.example.gccoffee.model.Email;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderItem;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.OrderRepository;
import com.example.gccoffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order createOrder(CreateOrderRequest orderRequest) {
        List<CreateOrderItemRequest> requestOrderItems = orderRequest.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();

        for (CreateOrderItemRequest requestOrderItem : requestOrderItems) {
            Product product = productRepository.findById(requestOrderItem.getProductId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
            OrderItem item = OrderItem.createOrderItem(product, requestOrderItem.getCategory(), requestOrderItem.getPrice(), requestOrderItem.getQuantity());
            orderItems.add(item);
        }

        Order order = Order.createOrder(new Email(orderRequest.getEmail()), orderRequest.getAddress(), orderRequest.getPostcode(), orderItems);
        return orderRepository.save(order);
    }
}

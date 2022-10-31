package com.example.gccoffee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private Category category;

    private long price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Product product, Category category, long price, int quantity, Order order) {
        this.product = product;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
    }

    public static OrderItem createOrderItem(Product product, Category category, long price, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.product = product;
        orderItem.category = category;
        orderItem.price = price;
        orderItem.quantity = quantity;
        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
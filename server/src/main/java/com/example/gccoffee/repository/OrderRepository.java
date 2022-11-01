package com.example.gccoffee.repository;

import com.example.gccoffee.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

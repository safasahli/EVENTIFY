package com.example.demo.services;

import com.example.demo.dtos.OrderRequest;
import com.example.demo.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(OrderRequest order);
    Order updateOrder(Long orderId, OrderRequest updatedOrderRequest);
    void deleteOrder(Long id);
}
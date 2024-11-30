package com.example.demo.services;

import com.example.demo.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem); // Save or update an order item
    List<OrderItem> getAllOrderItems();           // Retrieve all order items
    OrderItem getOrderItemById(Long id);          // Retrieve an order item by its ID
    void deleteOrderItemById(Long id);            // Delete an order item by its ID
}

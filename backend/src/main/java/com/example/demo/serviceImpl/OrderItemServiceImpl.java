package com.example.demo.services.impl;

import com.example.demo.entities.OrderItem;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    public OrderItem getOrderItemById(Long id) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);
        if (optionalOrderItem.isPresent()) {
            return optionalOrderItem.get();
        } else {
            throw new RuntimeException("Order item not found with id: " + id);
        }
    }

    @Override
    public void deleteOrderItemById(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order item not found with id: " + id);
        }
    }
}

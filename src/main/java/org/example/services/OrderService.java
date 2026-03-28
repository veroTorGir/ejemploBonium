package org.example.services;

import org.example.entities.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity order);

    List<OrderEntity> getAllOrders();

    OrderEntity getOrderById(Long id);

    void deleteOrder(Long id);
}

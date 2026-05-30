package org.example.services;

import org.example.entities.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity order);

    List<OrderEntity> getAllOrders();

    OrderEntity getOrderById(Long id);

    void deleteOrder(Long id);

    // Nuevo: obtener historial de pedidos por usuario
    List<OrderEntity> getOrdersByUser(Long userId);

    // Nuevo: obtener pedidos por producto (para proveedor/restaurante)
    List<OrderEntity> getOrdersByProduct(Long productId);
}

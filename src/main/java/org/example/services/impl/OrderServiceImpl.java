package org.example.services.impl;

import org.example.entities.OrderEntity;
import org.example.repositories.OrderRepository;
import org.example.repositories.UserRepository;
import org.example.repositories.ProductRepository;
import org.example.services.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        // Validar que el pedido tenga usuario y producto
        if (order.getUser() == null || order.getUser().getId() == null) {
            throw new RuntimeException("El pedido debe tener un usuario válido");
        }
        if (order.getProduct() == null || order.getProduct().getId() == null) {
            throw new RuntimeException("El pedido debe tener un producto válido");
        }
        Long userId = order.getUser().getId();
        Long productId = order.getProduct().getId();
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        boolean yaRedimido = !orderRepository.findByUserIdAndCreatedAtBetween(userId, startOfDay, endOfDay).isEmpty();
        if (yaRedimido) {
            throw new RuntimeException("Ya has redimido tu bono diario");
        }
        // Asignar entidad completa de usuario y producto
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        order.setProduct(productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        order.setCreatedAt(LocalDateTime.now());
        order.setEstado("pendiente");
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public List<OrderEntity> getOrdersByUser(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserIdAndCreatedAtBetween(
            userId,
            LocalDateTime.of(2000, 1, 1, 0, 0), // fecha muy antigua
            LocalDateTime.now().plusDays(1)
        );
        // Ordenar descendente por fecha
        orders.sort(Comparator.comparing(OrderEntity::getCreatedAt).reversed());
        return orders;
    }

    @Override
    public List<OrderEntity> getOrdersByProduct(Long productId) {
        List<OrderEntity> orders = orderRepository.findByProduct_Id(productId);
        orders.sort(Comparator.comparing(OrderEntity::getCreatedAt).reversed());
        return orders;
    }
}

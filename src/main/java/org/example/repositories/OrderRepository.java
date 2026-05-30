package org.example.repositories;

import org.example.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByProductId(Long productId);

    // Buscar órdenes por usuario y rango de fecha
    List<OrderEntity> findByUserIdAndCreatedAtBetween(Long userId, java.time.LocalDateTime start, java.time.LocalDateTime end);
    
    // Buscar órdenes por producto (para proveedor/restaurante)
    List<OrderEntity> findByProduct_Id(Long productId);
}
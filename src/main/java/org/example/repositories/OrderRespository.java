package org.example.repositories;

import org.example.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<OrderEntity, String> {
}

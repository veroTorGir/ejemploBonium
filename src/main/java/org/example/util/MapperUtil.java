package org.example.util;

import org.example.dto.*;
import org.example.entities.*;

public class MapperUtil {
    public static UserDTO toUserDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole() != null ? entity.getRole().name().toLowerCase() : null);
        return dto;
    }
    public static ProductDTO toProductDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrecio());
        dto.setType(entity.getType());
        return dto;
    }
    public static OrderDTO toOrderDTO(OrderEntity entity) {
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setModalidad(entity.getModalidad());
        dto.setUser(toUserDTO(entity.getUser()));
        dto.setProduct(toProductDTO(entity.getProduct()));
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    public static OrderEntity toOrderEntity(OrderDTO dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(dto.getId());
        entity.setModalidad(dto.getModalidad());
        // Asignar solo el id de usuario y producto para que el servicio los complete
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            UserEntity user = new UserEntity();
            user.setId(dto.getUser().getId());
            entity.setUser(user);
        }
        if (dto.getProduct() != null && dto.getProduct().getId() != null) {
            ProductEntity product = new ProductEntity();
            product.setId(dto.getProduct().getId());
            entity.setProduct(product);
        }
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setEstado(dto.getEstado());
        return entity;
    }
}

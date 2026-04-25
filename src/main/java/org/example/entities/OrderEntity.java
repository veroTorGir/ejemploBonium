package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modalidad;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "parent_order_id")
    private OrderEntity order;
    @OneToMany(mappedBy = "order")
    private List<OrderEntity> items;
    private LocalDateTime createdAt;

    public OrderEntity() {
    }

    public OrderEntity(Long id, String modalidad, UserEntity user, List<OrderEntity> items, LocalDateTime createdAt) {
        this.id = id;
        this.modalidad = modalidad;
        this.user = user;
        this.items = items;
        this.createdAt = createdAt;
    }

    public OrderEntity(Long id, String modalidad, UserEntity user, ProductEntity product, OrderEntity order, List<OrderEntity> items, LocalDateTime createdAt) {
        this.id = id;
        this.modalidad = modalidad;
        this.user = user;
        this.product = product;
        this.order = order;
        this.items = items;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<OrderEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderEntity> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

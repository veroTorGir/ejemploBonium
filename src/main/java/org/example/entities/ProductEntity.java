package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private float precio;
    @OneToMany(mappedBy = "product")
    private List<OrderEntity> items;

    public ProductEntity(Long id, String name, float precio, String description, List<OrderEntity> items, boolean plato) {
        this.id = id;
        this.name = name;
        this.precio = precio;
        this.description = description;
        this.items = items;
        this.plato = plato;
    }

    //true = plato
    //false= adición
    private boolean plato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isPlato() {
        return plato;
    }

    public void setPlato(boolean plato) {
        this.plato = plato;
    }
}

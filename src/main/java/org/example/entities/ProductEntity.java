package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private List<OrderEntity> items;

    public ProductEntity() {}

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

    @JsonProperty("price")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @JsonIgnore
    public boolean isPlato() {
        return plato;
    }

    public void setPlato(boolean plato) {
        this.plato = plato;
    }

    public String getType() {
        return plato ? "plato" : "adicional";
    }
}

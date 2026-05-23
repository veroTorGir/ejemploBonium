// Paquete donde está ubicada la clase
package org.example.entities;

// Importaciones de Jakarta Persistence (JPA) para mapeo ORM
import jakarta.persistence.*;

// Importación para manejar fechas y horas
import java.time.LocalDateTime;

/**
 * Entidad que representa un Bono en la base de datos
 * Mapeada a la tabla "bono_entity" automáticamente por JPA
 */
@Entity
public class BonoEntity {
    // Marca este campo como la clave primaria de la tabla
    @Id
    // Genera el ID automáticamente usando estrategia de identidad (auto-increment en MySQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // Identificador único del bono

    private String name;      // Nombre del bono (ej: "Bono Premium", "Bono Descuento")

    private String descripcion; // Descripción detallada del bono

    private Double precio;    // Precio del bono

    private LocalDateTime createdAt; // Fecha y hora de creación del registro

    // Constructor vacío requerido por JPA para instanciar entidades desde la BD
    public BonoEntity() {
    }

    // Constructor con todos los parámetros para crear instancias directamente
    public BonoEntity(Long id, String name, String descripcion, Double precio, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.createdAt = createdAt;
    }

    // Getter: Método público para obtener el valor del campo 'id'
    public Long getId() {
        return id;
    }

    // Setter: Método público para modificar el valor del campo 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter: Obtiene el nombre del bono
    public String getName() {
        return name;
    }

    // Setter: Modifica el nombre del bono
    public void setName(String name) {
        this.name = name;
    }

    // Getter: Obtiene la descripción del bono
    public String getDescripcion() {
        return descripcion;
    }

    // Setter: Modifica la descripción del bono
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter: Obtiene el precio del bono
    public Double getPrecio() {
        return precio;
    }

    // Setter: Modifica el precio del bono
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    // Getter: Obtiene la fecha de creación
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter: Modifica la fecha de creación
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

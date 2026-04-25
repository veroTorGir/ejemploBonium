// Paquete donde está ubicada la interfaz
package org.example.repositories;

// Importación de la entidad BonoEntity
import org.example.entities.BonoEntity;

// Importación de Spring Data JPA para repositorios
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para operaciones CRUD de BonoEntity
 * Extiende JpaRepository que proporciona métodos básicos:
 * - save(), findById(), findAll(), deleteById(), etc.
 * 
 * @param <BonoEntity> La entidad que gestiona este repositorio
 * @param <Long> El tipo de dato de la clave primaria
 */
public interface BonoRepository extends JpaRepository<BonoEntity, Long> {
    // No necesita métodos adicionales, JpaRepository ya ofrece:
    // - save(bono) → guarda o actualiza un bono
    // - findById(id) → busca un bono por su ID
    // - findAll() → devuelve todos los bonos
    // - deleteById(id) → elimina un bono por su ID
    // - existsById(id) → verifica si existe un bono
}

// Paquete donde está ubicada la interfaz de servicio
package org.example.services;

// Importación de la entidad BonoEntity
import org.example.entities.BonoEntity;

// Importación de List para retornar colecciones de bonos
import java.util.List;

/**
 * Interfaz que define el contrato de operaciones de negocio para Bono
 * Define los métodos que debe implementar la capa de servicio
 */
public interface BonoService {
    /**
     * Crea un nuevo bono en el sistema
     * @param bono El bono a crear
     * @return El bono creado con su ID asignado
     */
    BonoEntity createBono(BonoEntity bono);

    /**
     * Obtiene todos los bonos registrados
     * @return Lista de todos los bonos
     */
    List<BonoEntity> getAllBonos();

    /**
     * Busca un bono por su ID
     * @param id El ID del bono a buscar
     * @return El bono encontrado
     * @throws RuntimeException si no se encuentra el bono
     */
    BonoEntity getBonoById(Long id);

    /**
     * Elimina un bono por su ID
     * @param id El ID del bono a eliminar
     */
    void deleteBono(Long id);
}
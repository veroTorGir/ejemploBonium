// Paquete donde está ubicada la implementación del servicio
package org.example.services.impl;

// Importación de la entidad BonoEntity
import org.example.entities.BonoEntity;
// Importación del repositorio para acceso a datos
import org.example.repositories.BonoRepository;
// Importación de la interfaz del servicio
import org.example.services.BonoService;
// Anotación de Spring para marcar esta clase como componente de servicio
import org.springframework.stereotype.Service;

// Importaciones de Java para fechas y colecciones
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Implementación de la interfaz BonoService
 * Contiene la lógica de negocio para operaciones con bonos
 */
@Service  // Indica que es un componente gestionado por Spring (bean)
public class BonoServiceImpl implements BonoService {

    // Inyección de dependencias del repositorio (final = inmutable)
    private final BonoRepository bonoRepository;

    // Constructor con inyección de dependencias
    public BonoServiceImpl(BonoRepository bonoRepository) {
        this.bonoRepository = bonoRepository;
    }

    /**
     * Crea un nuevo bono
     * 1. Asigna la fecha/hora actual al campo createdAt
     * 2. Guarda el bono en la base de datos mediante el repositorio
     * 3. Retorna el bono guardado con su ID generado
     */
    @Override
    public BonoEntity createBono(BonoEntity bono) {
        bono.setCreatedAt(LocalDateTime.now());  // Asigna fecha actual
        return bonoRepository.save(bono);        // Persiste en BD y retorna
    }

    /**
     * Obtiene todos los bonos
     * Delega al repositorio la consulta de todos los registros
     */
    @Override
    public List<BonoEntity> getAllBonos() {
        return bonoRepository.findAll();  // Consulta todos los bonos en BD
    }

    /**
     * Busca un bono por su ID
     * 1. Consulta el repositorio por el ID
     * 2. Si no existe, lanza una excepción RuntimeException
     * 3. Retorna el bono encontrado
     */
    @Override
    public BonoEntity getBonoById(Long id) {
        // findById retorna un Optional: si tiene valor lo retorna, si no lanza excepción
        return bonoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bono not found"));
    }

    /**
     * Elimina un bono por su ID
     * Utiliza deleteAllById que es más eficiente que deleteById para un solo registro
     */
    @Override
    public void deleteBono(Long id) {
        // Elimina el bono de la base de datos
        bonoRepository.deleteAllById(Collections.singleton(id));
    }
}